package com.example.mvctutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import com.example.mvctutorial.databinding.ActivityMainBinding
import com.example.mvctutorial.homecontent.HomeFragment
import com.example.mvctutorial.homecontent.StoryBoardFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val storyBoardFragment = StoryBoardFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, homeFragment)
            .commit()

    }

    override fun onStart() {
        super.onStart()

        homeFragment.binding.cuSectionShortcut.setOnClickListener {

            homeFragment.binding.appbar.setExpanded(true, true)

            val fadeInRes = android.R.anim.fade_in
            val fadeOutRes = android.R.anim.fade_out

            val bundle = Bundle()

            val banner = homeFragment.binding.cuSectionBanner
            banner.let {
                bundle.putInt("bannerHeight", banner.height)
            }

            storyBoardFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(fadeInRes, fadeOutRes, fadeInRes, fadeOutRes)
                .add(R.id.content_frame, storyBoardFragment)
                .commit()
        }
    }

}
