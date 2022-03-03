package com.example.mvctutorial.network.module

import com.example.mvctutorial.network.module.apiservice.PostApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postApi: PostApi = retrofit.create(PostApi::class.java)
}
