package com.example.mvctutorial.homecontent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvctutorial.R
import com.example.mvctutorial.databinding.ActivityBannerBinding

class BannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBannerBinding

    private val homeFragment = HomeFragment()
    private val storyBoardFragment = StoryBoardFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBannerBinding.inflate(layoutInflater)
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