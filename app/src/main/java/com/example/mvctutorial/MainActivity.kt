package com.example.mvctutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mvctutorial.network.Post
import com.example.mvctutorial.network.RetrofitServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainView = LayoutInflater.from(this).inflate(R.layout.activity_main, null, false)

//        val storyBoardView = View(this).apply {
//            layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT).apply {
//                bottomToTop = mainView.findViewById<TextView>(R.id.cuSectionShortcut).id
//            }
//        }
//        val layout = ConstraintLayout(this).apply {
//            layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
//            addView(mainView)
//            addView(storyBoardView)
//        }

        setContentView(mainView)
    }
}
