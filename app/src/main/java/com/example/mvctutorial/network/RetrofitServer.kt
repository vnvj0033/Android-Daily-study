package com.example.mvctutorial.network

import retrofit2.Call
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

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)