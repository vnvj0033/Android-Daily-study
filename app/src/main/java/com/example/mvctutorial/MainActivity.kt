package com.example.mvctutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvctutorial.homecontent.HomeFragment
import com.example.mvctutorial.homecontent.StoryBoardFragment

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val storyBoardFragment = StoryBoardFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.content_frame, homeFragment).commit()
    }

}
