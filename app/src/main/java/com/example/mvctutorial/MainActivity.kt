package com.example.mvctutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mvctutorial.network.Post
import com.example.mvctutorial.network.RetrofitServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadPostWithExecute("1")

//        loadPostWithEnqueue("2")
    }

    private fun loadPostWithEnqueue(id: String) {
        val callback = object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.d(localClassName,"post = ${response.body()}")
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        }

        RetrofitServer.postAPI.getPost(id).enqueue(callback)
    }

    private fun loadPostWithExecute(id: String) {
        Thread{
            val response = RetrofitServer.postAPI.getPost(id).execute()

            Log.d(localClassName,"post = ${response.body()}")
        }.start()
    }
}