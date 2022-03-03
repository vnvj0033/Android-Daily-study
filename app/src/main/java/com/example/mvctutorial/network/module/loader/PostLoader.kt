package com.example.mvctutorial.network.module.loader

import com.example.mvctutorial.network.module.DataListener
import com.example.mvctutorial.network.module.RetrofitServer
import com.example.mvctutorial.network.module.baseCallback
import com.example.mvctutorial.network.module.model.Post

class PostLoader {
    private val postApi = RetrofitServer.postApi

    fun getPost(map: Map<String, String>, dataListener: DataListener<Post>) {
        postApi.getPost(map).enqueue(baseCallback(dataListener))
    }

    fun getPost(id: Int, dataListener: DataListener<Post>) {
        postApi.getPost(id).enqueue(baseCallback(dataListener))
    }
}