package com.example.mvctutorial.network

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mvctutorial.R
import com.example.mvctutorial.network.module.*
import com.example.mvctutorial.network.module.loader.PostLoader
import com.example.mvctutorial.network.module.model.Post

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        loadPost(1)
    }

    private fun loadPost(id: Int) {

        val postLoader = PostLoader()

        Log.d("testsyyoo", "요청")
        postLoader.getPost(id, object : DataListener<Post> {
            override fun onSuccess(data: Post) {
                Log.d("testsyyoo", "응답 성공")
                findViewById<TextView>(R.id.textView).text = loadInt().toString()
            }

            override fun onFailed() {
                Log.d("testsyyoo", "응답 실패")
            }
        })
    }

    private fun loadInt() : Int {
        val sp = getSharedPreferences("myLocal", MODE_PRIVATE)
        val i = sp.getInt("key", -1)

        val editor = sp.edit()

        try {
            editor.putInt("key", i+1)
            editor.commit()
        } catch (e: Exception) { }
        return i + 1
    }
}
