package com.example.mvctutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvctutorial.compose.ComposeActivity
import com.example.mvctutorial.databinding.ActivityMainBinding
import com.example.mvctutorial.homecontent.BannerActivity
import com.example.mvctutorial.navigate.NavigateActivity
import com.example.mvctutorial.network.RetrofitActivity
import com.example.mvctutorial.uitest.UiTestActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activity = this
    }

    val bannerClick = View.OnClickListener{
        val intent = Intent(this, BannerActivity::class.java)
        startActivity(intent)
    }

    val uitestClick = View.OnClickListener {
        val intent = Intent(this, UiTestActivity::class.java)
        startActivity(intent)
    }

    val navClick = View.OnClickListener {
        val intent = Intent(this, NavigateActivity::class.java)
        startActivity(intent)
    }

    val netClick = View.OnClickListener {
        val intent = Intent(this, RetrofitActivity::class.java)
        startActivity(intent)
    }

    val composeClick = View.OnClickListener {
        val intent = Intent(this, ComposeActivity::class.java)
        startActivity(intent)
    }
}
