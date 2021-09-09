package com.example.mvctutorial.network

import android.content.Context
import com.google.api.client.http.HttpRequest
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.HttpResponse
import com.google.api.client.http.javanet.NetHttpTransport
import com.joara.mobile.util.Util
import java.io.InputStream

object NetDataLoader {

    private const val FIXED_VERSION = "1.23.0"
    private const val FIXED_USER_AGENT = "Google-HTTP-Java-Client/$FIXED_VERSION (gzip)"

    private val HTTP_TRANSPORT = NetHttpTransport()

    fun executeData(context: Context, request: NetItemRequest): NetItemResponse {

        val response = NetItemResponse().apply {
            call = NetItemResponse.NETITEMRESPONSE_CALL_CODE_API
        }


        val requestFactory = HTTP_TRANSPORT.createRequestFactory { httpRequest: HttpRequest ->
            // TimeOut 설정
            httpRequest.connectTimeout = NetInfo.CONNECT_TIME_LIMIT
            httpRequest.readTimeout = NetInfo.CONNECT_TIME_LIMIT

            // 재시도 횟수 설정정
            httpRequest.numberOfRetries = 30
        }


        val httpRequest: HttpRequest? = null        // HttpHeader : {accept-encoding=[gzip]}
        val httpResponse: HttpResponse? = null      // {cache-control=[no-cache="set-cookie"], content-encoding=[gzip], content-type=[application/json; charset=utf-8], date=[Fri, 11 Dec 2015 06:26:16 GMT], access-control-allow-headers=[api_key, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method], access-control-allow-methods=[GET, POST, OPTIONS, PUT, DELETE], access-control-allow-origin=[*], cf-ray=[252f1df3b4831291-ICN], connection=[keep-alive], server=[cloudflare-nginx], set-cookie=[__cfduid=d4131745f691c7f3d7325b6aa6a71f8531449815176; expires=Sat, 10-Dec-16 06:26:16 GMT; path=/; domain=.joara.com; HttpOnly, AWSELB=4DB9B1A50EF732E73D3AF5EE4127B0F58FC0A1D77745013B6BEC9FA489BA05C9C2FA26CA40957678C3D462397E1062A5F144E4BB48F915BDEDB55E73AA6CF92EDAEB262218;PATH=/;MAX-AGE=300], transfer-encoding=[chunked], x-android-received-millis=[1449901513256], x-android-sent-millis=[1449901513071]}

        val inputStream: InputStream? = null

        var beforeKind = Util.getNetworkState(context)

        val startTime = System.currentTimeMillis()


        /*********** 상단 작업중 ************/
        return response
    }
}