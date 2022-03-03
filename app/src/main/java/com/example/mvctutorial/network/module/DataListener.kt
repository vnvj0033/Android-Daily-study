package com.example.mvctutorial.network.module

interface DataListener<T> {
    fun onSuccess(data: T)
    fun onFailed() {}
}