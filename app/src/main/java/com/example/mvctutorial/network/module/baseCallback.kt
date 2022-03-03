package com.example.mvctutorial.network.module

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> baseCallback(dataListener: DataListener<T>): Callback<T> {

    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {

            val result = response.body()

            if (response.isSuccessful && result != null) {
                dataListener.onSuccess(result)
            } else {
                dataListener.onFailed()
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            dataListener.onFailed()
        }
    }
}