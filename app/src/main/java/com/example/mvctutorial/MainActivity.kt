package com.example.mvctutorial

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvctutorial.compose.ComposeActivity
import com.example.mvctutorial.dagger2.sandbox.di.SandBoxActivity
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

    val bannerClick = startActivity(BannerActivity::class.java)
    val uitestClick = startActivity(UiTestActivity::class.java)
    val navClick = startActivity(NavigateActivity::class.java)
    val netClick = startActivity(RetrofitActivity::class.java)
    val composeClick = startActivity(ComposeActivity::class.java)
    val sandBoxClick = startActivity(SandBoxActivity::class.java)

    private fun startActivity(cls: Class<out Activity>) = View.OnClickListener {
        val intent = Intent(this, cls)
        startActivity(intent)
    }
}
