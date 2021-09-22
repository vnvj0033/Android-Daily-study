package com.example.mvctutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvctutorial.homecontent.HomeContentAdapter
import com.example.mvctutorial.homecontent.StoryBoardFragment
import com.example.mvctutorial.network.Post
import com.example.mvctutorial.network.RetrofitServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.homeContentView).run {
            adapter = HomeContentAdapter(30)
        }

        findViewById<TextView>(R.id.cuSectionShortcut).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .add(R.id.content_frame, StoryBoardFragment())
                .commit()
        }
    }

}
