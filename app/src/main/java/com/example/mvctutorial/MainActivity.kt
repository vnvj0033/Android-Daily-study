package com.example.mvctutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvctutorial.databinding.ActivityMainBinding
import com.example.mvctutorial.homecontent.BannerActivity
import com.example.mvctutorial.uitest.UiTestActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bannerButton.setOnClickListener {
            val intent = Intent(this, BannerActivity::class.java)
            startActivity(intent)
        }

        binding.uitestButton.setOnClickListener {
            val intent = Intent(this, UiTestActivity::class.java)
            startActivity(intent)
        }

    }
}
