package com.example.mvctutorial.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mvctutorial.R
import com.example.mvctutorial.network.module.*
import com.example.mvctutorial.network.module.model.Post
import com.example.mvctutorial.network.module.loader.PostLoader

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        loadPost(1)

    }

    private fun loadPost(id: Int) {
        val requester = Requester.getPost(id.toString())

        val postLoader = PostLoader()

        postLoader.getPost(requester, object : DataListener<Post> {
            override fun onSuccess(data: Post) {
                findViewById<TextView>(R.id.textView).text = data.title
            }
        })
    }
}