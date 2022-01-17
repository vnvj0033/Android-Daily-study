package com.example.mvctutorial.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mvctutorial.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)


        RetrofitServer.postAPI.getPost("1").enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()

                    findViewById<TextView>(R.id.textView).text = post?.title
                } else {
                    findViewById<TextView>(R.id.textView).text = "통신 실패"
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                findViewById<TextView>(R.id.textView).text = "통신 실패"
            }

        })

        loadPostWithEnqueue("2")
        loadPostWithExecute("3")
    }
}