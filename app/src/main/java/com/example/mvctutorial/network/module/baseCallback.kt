package com.example.mvctutorial.network.module

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> baseCallback(retrofitDataListener: DataListener<T>): Callback<T> {

    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {

            val result = response.body()

            if (response.isSuccessful && result != null) {
                retrofitDataListener.onSuccess(result)
            } else {
                retrofitDataListener.onFailed()
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            retrofitDataListener.onFailed()
        }
    }
}