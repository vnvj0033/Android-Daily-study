package com.example.mvctutorial.network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.google.api.client.http.*
import com.google.api.client.http.javanet.NetHttpTransport
import com.joara.mobile.BuildConfig
import com.joara.mobile.info.InfoConfig
import com.joara.mobile.util.Util
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.HashMap

object NetDataLoader {

    private const val FIXED_VERSION = "1.23.0"
    private const val FIXED_USER_AGENT = "Google-HTTP-Java-Client/$FIXED_VERSION (gzip)"

    private val HTTP_TRANSPORT = NetHttpTransport()

    fun executeData(context: Context, request: NetItemRequest): NetItemResponse {

        val response = NetItemResponse().apply {
            call = NetItemResponse.NETITEMRESPONSE_CALL_CODE_API
        }

        val httpRequest: HttpRequest                // HttpHeader : {accept-encoding=[gzip]}
        var httpResponse: HttpResponse? = null      // {cache-control=[no-cache="set-cookie"], content-encoding=[gzip], content-type=[application/json; charset=utf-8], date=[Fri, 11 Dec 2015 06:26:16 GMT], access-control-allow-headers=[api_key, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method], access-control-allow-methods=[GET, POST, OPTIONS, PUT, DELETE], access-control-allow-origin=[*], cf-ray=[252f1df3b4831291-ICN], connection=[keep-alive], server=[cloudflare-nginx], set-cookie=[__cfduid=d4131745f691c7f3d7325b6aa6a71f8531449815176; expires=Sat, 10-Dec-16 06:26:16 GMT; path=/; domain=.joara.com; HttpOnly, AWSELB=4DB9B1A50EF732E73D3AF5EE4127B0F58FC0A1D77745013B6BEC9FA489BA05C9C2FA26CA40957678C3D462397E1062A5F144E4BB48F915BDEDB55E73AA6CF92EDAEB262218;PATH=/;MAX-AGE=300], transfer-encoding=[chunked], x-android-received-millis=[1449901513256], x-android-sent-millis=[1449901513071]}

        var inputStream: InputStream? = null

        val beforeKind = Util.getNetworkState(context)

        val startTime = System.currentTimeMillis()

        try {
            httpRequest = when (request.method) {
                NetInfo.NET_ITEM_REQUEST_VALUE_METHOD_POST -> createPostRequest(request)
                NetInfo.NET_ITEM_REQUEST_VALUE_METHOD_PUT -> createPutRequest(request)
                NetInfo.NET_ITEM_REQUEST_VALUE_METHOD_DELETE -> createDeleteRequest(request)
                else -> createGetRequest(request)
            }

            // UserAgent 고정 (UserAgent값이 변경될 경우 회원 로그인 토큰값이 변경됨)
            httpRequest.headers.userAgent = FIXED_USER_AGENT
            httpRequest.suppressUserAgentSuffix = true

            httpResponse = httpRequest.execute()

            if (httpResponse.statusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpResponse.content
                val responseContent = Util.getString(inputStream)

                response.status = httpResponse.statusCode
                response.result = responseContent

            } else {
                createErrorResponse(context, request, response, httpResponse, beforeKind, startTime)
            }

            response.useAPI =
                if (Util.isApiDev()) "\n\n" + request.url.replace(InfoConfig.INSTANCE.getConfigCode(BuildConfig.joara_data_api_host), "")
                else request.url.replace(InfoConfig.INSTANCE.getConfigCode(BuildConfig.joara_data_api_host), "")

            /****** 상단 작업 완료 ******/

        } catch (e: SocketTimeoutException) {
            val moreData = JSONObject()
            if (Util.isFilledString(response.result)) {
                moreData.put("NET_STATUS_RESULT_ERROR_HTTP", response.result)
            }

            with(response) {
                status = NetInfo.NET_STATUS_RESULT_ERROR_TIMEOUT
                result = "응답시간이 초과되었습니다.\n다시 시도 하시겠습니까?"
                errorException = e
                errorCustomData = Util.makeErrorException(
                    context,
                    "NET_STATUS_RESULT_ERROR_TIMEOUT",
                    request.url,
                    Util.getParameterString(request.parameter),
                    beforeKind,
                    startTime,
                    moreData.toString()
                )
            }
        } catch (e: UnknownHostException) {
            val moreData = JSONObject()
            if (Util.isFilledString(response.result)) {
                moreData.put("NET_STATUS_RESULT_ERROR_HTTP", response.result)
            }

            response.status = NetInfo.NET_STATUS_RESULT_ERROR_UNKNOWHOST
            response.result = "네트워크 연결 상태를 확인하여 주시기 바랍니다.\n다시 시도 하시겠습니까?"
            response.errorException = e
            response.errorCustomData = Util.makeErrorException(
                context,
                "NET_STATUS_RESULT_ERROR_UNKNOWHOST",
                request.url,
                Util.getParameterString(request.parameter),
                beforeKind,
                startTime,
                moreData.toString()
            )
        } catch (e: IOException) {
            val moreData = JSONObject()
            if (Util.isFilledString(response.result)) {
                moreData.put("NET_STATUS_RESULT_ERROR_HTTP", response.result)
            }

            response.status = NetInfo.NET_STATUS_RESULT_ERROR_IO
            response.result = "일시적으로 접속에 어려움이 있습니다.\n다시 시도 하시겠습니까?"
            response.errorException = e
            response.errorCustomData = Util.makeErrorException(
                context,
                "NET_STATUS_RESULT_ERROR_IO",
                request.url,
                Util.getParameterString(request.parameter),
                beforeKind,
                startTime,
                moreData.toString()
            )
        } catch (e: Exception) {
            val moreData = JSONObject()
            if (Util.isFilledString(response.result)) {
                moreData.put("NET_STATUS_RESULT_ERROR_HTTP", response.result)
            }

            response.status = NetInfo.NET_STATUS_RESULT_ERROR_EXCEPTION
            response.result = "데이터를 읽을 수 없습니다.\n다시 시도 하시겠습니까?"
            response.errorException = e
            response.errorCustomData = Util.makeErrorException(
                context,
                "NET_STATUS_RESULT_ERROR_EXCEPTION",
                request.url,
                Util.getParameterString(request.parameter),
                beforeKind,
                startTime,
                moreData.toString()
            )

        } finally {
            try {
                inputStream?.close()
                httpResponse?.disconnect()
            } catch (e: IOException) {
                Util.showCommonLog(Thread.currentThread().stackTrace[2].toString(), e);
            }
        }

        return response
    }

    fun executeImageBitmap(
        context: Context,
        url: String,
        saveName: String,
        _inSampleSize: Int,
        imgFormat: CompressFormat,
        preservation: Boolean,
    ): Bitmap? {
        var inSampleSize = _inSampleSize

        var resultBitmap: Bitmap? = null

        val requestFactory = NetConnect.HTTP_TRANSPORT.createRequestFactory()

        val httpRequest: HttpRequest
        var httpResponse: HttpResponse? = null

        var inputStream: InputStream? = null


        try {
            val filePathString = Util.getServiceableFilePath(context)
            var file: File? = null
            if (Util.isFilledString(filePathString)) {
                val filePath = File(filePathString)
                if (!filePath.isDirectory) {
                    filePath.mkdirs()
                    if (filePath.isDirectory) {
                        filePath.createNewFile()
                    }
                }

                // 저장된 파일이 있는지
                if (Util.isFilledString(saveName)) {
                    file = File(filePath, saveName)
                    if (file.exists() && file.length() > 0) {
                        // 이미지 파일을 다운 받은지 하루가 지났으면 새로 다운 받도록.
//                        String fileCreated = Util.getStringToDate(new Date(file.lastModified()));
//                        String currentDate = Util.getDateCurrentFull();
//
//                        if (Util.getDateDifference(fileCreated, currentDate, Util.DATE_DIFFERENCE_KIND_DAY) < 1) {
//                            //                        result = BitmapFactory.decodeFile(filePath + "/" + savename);
                        file.setLastModified(System.currentTimeMillis())
                        if (Build.VERSION.SDK_INT >= 28) {
                            val source = ImageDecoder.createSource(context.contentResolver, Uri.fromFile(file))
                            resultBitmap = ImageDecoder.decodeBitmap(source)
                        }else {
                            resultBitmap = MediaStore.Images.Media.getBitmap(context.contentResolver,Uri.fromFile(file))
                        }
                        return resultBitmap
                        //                        }
                    }
                }
            }
            if (url.startsWith("http")) {
                val genericUrl = GenericUrl(url)
                httpRequest = requestFactory.buildGetRequest(genericUrl)
                httpRequest.connectTimeout = NetInfo.CONNECT_TIME_LIMIT
                val requestHttpHeaders = httpRequest.headers
                requestHttpHeaders.acceptEncoding = null
                requestHttpHeaders.contentType = "image/jpg"
                httpRequest.headers = requestHttpHeaders
                httpResponse = httpRequest.execute()
                if (httpResponse.statusCode == HttpURLConnection.HTTP_OK) {
                    // 이미지의 크기가 클 경우
                    val strContentLength =
                        httpResponse.headers.getFirstHeaderStringValue("content-length")
                    val longContentLength = strContentLength.toLong()
                    if (longContentLength > 1000000) {
                        inSampleSize = 4
                    } else if (longContentLength > 500000) {
                        inSampleSize = 2
                    }

                    // 이미지 다운로드
                    inputStream = httpResponse.content
                    resultBitmap = Util.getBitmap(inputStream, inSampleSize)
                    if (file != null && resultBitmap != null) {
                        val os = FileOutputStream(file)
                        resultBitmap.compress(imgFormat, 96, os)
                        os.flush()
                        os.close()
                        if (file.exists()) {
                            if (preservation) {
                                val lastDate = Util.getDateToString("99991212235959").time
                                file.setLastModified(lastDate)
                            }

//                            result = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.fromFile(file));
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Util.showCommonLog(Thread.currentThread().stackTrace[2].toString(), e)
        } finally {
            try {
                inputStream?.close()
                httpResponse?.disconnect()
            } catch (e: IOException) {
                Util.showCommonLog(Thread.currentThread().stackTrace[2].toString(), e)
            }
        }

        return resultBitmap
    }

    private fun createErrorResponse(
        context: Context,
        request: NetItemRequest,
        response: NetItemResponse,
        httpResponse: HttpResponse,
        beforeKind: String,
        startTime: Long,
    ) {

        response.status = NetInfo.NET_STATUS_RESULT_ERROR_DATA
        response.result = "일시적으로 접속에 어려움이 있습니다.\n다시 시도 하시겠습니까?"

        var apiErrorData = ""

        if (httpResponse.content != null) {
            try {
                val jsonObject = JSONObject()
                jsonObject.put("API_STATUSCODE", httpResponse.statusCode)
                jsonObject.put("API_STATUSMESSAGE", httpResponse.statusMessage)
                jsonObject.put("API_CONTENT", Util.getString(httpResponse.content))
                apiErrorData = jsonObject.toString()
            } catch (e: Exception) {
                Util.showCommonLog(Thread.currentThread().stackTrace[2].toString(), e)
            }
            response.errorCustomData = Util.makeErrorException(
                context,
                "NET_STATUS_RESULT_ERROR_DATA",
                request.url,
                Util.getParameterString(request.parameter),
                beforeKind,
                startTime,
                apiErrorData
            )
        } else {
            response.errorCustomData = Util.makeErrorException(
                context,
                "NET_STATUS_RESULT_ERROR_DATA",
                request.url,
                Util.getParameterString(request.parameter),
                beforeKind,
                startTime,
                apiErrorData
            )
        }
    }


    private fun createGetRequest(request: NetItemRequest): HttpRequest {
        val url = getGenericUrlFromRequestParameter(request)
        return requestFactory().buildGetRequest(url)

    }

    private fun createDeleteRequest(request: NetItemRequest): HttpRequest {
        val url = getGenericUrlFromRequestParameter(request)
        return requestFactory().buildDeleteRequest(url)
    }

    private fun getGenericUrlFromRequestParameter(request: NetItemRequest): GenericUrl {
        val parameterStr =
            if (request.parameter != null) Util.getParameterString(request.parameter)
            else ""

        return GenericUrl(request.url + parameterStr)
    }

    private fun createPutRequest(request: NetItemRequest): HttpRequest {
        val url = GenericUrl(request.url)

        val params = HashMap<String, String>()
        if (request.parameter.size > 0) {
            for (param in request.parameter) {
                params[param.name] = param.value
            }
        }

        return requestFactory().buildPutRequest(url, UrlEncodedContent(params))
    }

    private fun createPostRequest(request: NetItemRequest): HttpRequest {
        val url = GenericUrl(request.url)

        if (Util.isFilledString(request.imagePath)) {
            val content =
                MultipartContent().setMediaType(HttpMediaType("multipart/form-data").setParameter("boundary",
                    "__END_OF_PART__"))

            // Add file
            val imgFile = File(request.imagePath)
            val fileContent = FileContent("image/jpeg", imgFile)
            val partFile = MultipartContent.Part(fileContent)
            partFile.headers = HttpHeaders().set("Content-Disposition",
                String.format("form-data; name=\"image\"; filename=\"%s\"", imgFile.name))
            content.addPart(partFile)

            // Add parameters
            if (request.parameter.size > 0) {
                for (param in request.parameter) {
                    val partParam =
                        MultipartContent.Part(ByteArrayContent(null, param.value.toByteArray()))
                    partParam.headers = HttpHeaders().set("Content-Disposition",
                        String.format("form-data; name=\"%s\"", param.name))
                    content.addPart(partParam)
                }
            }
            return requestFactory().buildPostRequest(url, content)
        } else {
            val params = HashMap<String, String>()
            if (request.parameter.size > 0) {
                for (param in request.parameter) {
                    params[param.name] = param.value
                }
            }
            return requestFactory().buildPostRequest(url, UrlEncodedContent(params))
        }
    }

    private fun requestFactory() = HTTP_TRANSPORT.createRequestFactory { httpRequest: HttpRequest ->
        with(httpRequest) {
            // TimeOut 설정
            connectTimeout = NetInfo.CONNECT_TIME_LIMIT
            readTimeout = NetInfo.CONNECT_TIME_LIMIT

            // 재시도 횟수 설정정
            numberOfRetries = 30
        }
    }
}