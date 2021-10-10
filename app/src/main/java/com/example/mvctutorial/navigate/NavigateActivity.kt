package com.example.mvctutorial.navigate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvctutorial.databinding.ActivityNavigateBinding

class NavigateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
