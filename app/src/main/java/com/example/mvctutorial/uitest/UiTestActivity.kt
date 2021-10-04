package com.example.mvctutorial.uitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvctutorial.databinding.ActivityUiTestBinding

class UiTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUiTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUiTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.languageTextView.text = "korea"

    }
}