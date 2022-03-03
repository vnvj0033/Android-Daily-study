package com.example.mvctutorial.network.module

object Requester {
    fun getPost(id: String) : Map<String, String> {

        val requester: MutableMap<String, String> = HashMap()
        requester["id"] = id

        return requester
    }
}