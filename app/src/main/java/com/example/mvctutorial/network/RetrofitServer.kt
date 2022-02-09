package com.example.mvctutorial.network

import android.util.Log
import androidx.annotation.Keep
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object RetrofitServer {
    private val headerInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .removeHeader("User-Agent")
            .addHeader("User-Agent", "Google-HTTP-Java-Client/1.23.0 (gzip)")
            .build()
        chain.proceed(request)
    }

    private val logInterceptor = Interceptor { chain ->
        val request = chain.request()
        Log.d("testsyyoo", request.headers().toString())
        chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(logInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postAPI: PostService = retrofit.create(PostService::class.java)
}

interface PostService {
    @GET("posts/{id}")
    fun getPost(@Path("id") id: String): Call<Post>
}

@Keep
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

fun loadPostWithEnqueue(id: String) {
    val callback = object: Callback<Post> {
        override fun onResponse(call: Call<Post>, response: Response<Post>) {
            Log.d("loadPostWithEnqueue","post = ${response.body()}")
        }

        override fun onFailure(call: Call<Post>, t: Throwable) { }
    }

    RetrofitServer.postAPI.getPost(id).enqueue(callback)
}

fun loadPostWithExecute(id: String) {
    Thread{
        val response = RetrofitServer.postAPI.getPost(id).execute()

        Log.d("loadPostWithExecute","post = ${response.body()}")
    }.start()
}
