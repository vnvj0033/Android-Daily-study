package com.example.mvctutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.example.mvctutorial.homecontent.HomeFragment
import com.example.mvctutorial.homecontent.StoryBoardFragment

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val storyBoardFragment = StoryBoardFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.content_frame, homeFragment).commit()

        homeFragment.view?.findViewById<TextView>(R.id.cuSectionShortcut)?.setOnClickListener {

            val fadeInRes = android.R.anim.fade_in
            val fadeOutRes = android.R.anim.fade_out

            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(fadeInRes, fadeOutRes, fadeInRes, fadeOutRes)
                .add(R.id.content_frame, storyBoardFragment)
                .commit()
        }
    }

}
