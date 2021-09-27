package com.example.mvctutorial.network

import android.util.Log
import androidx.annotation.Keep
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object RetrofitServer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
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


private fun loadPostWithEnqueue(id: String) {
    val callback = object: Callback<Post> {
        override fun onResponse(call: Call<Post>, response: Response<Post>) {
            Log.d("loadPostWithEnqueue","post = ${response.body()}")
        }

        override fun onFailure(call: Call<Post>, t: Throwable) {

        }

    }

    RetrofitServer.postAPI.getPost(id).enqueue(callback)
}

fun loadPostWithExecute(id: String) {
    Thread{
        val response = RetrofitServer.postAPI.getPost(id).execute()

        Log.d("loadPostWithExecute","post = ${response.body()}")
    }.start()
}