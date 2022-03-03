package com.example.mvctutorial.network.module.service

import com.example.mvctutorial.network.module.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PostApi {
    @GET("posts/{id}")
    fun getPost(@QueryMap queryMap: Map<String, String>): Call<Post>
}