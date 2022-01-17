package com.example.mvctutorial.uitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvctutorial.databinding.ActivityUiTestBinding

class UiTestActivity : AppCompatActivity() {

    companion object {
        const val beforeText = "korea"
        const val afterText = "KOR"
    }

    private lateinit var binding: ActivityUiTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUiTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = beforeText

        binding.button.setOnClickListener {
            binding.textView.text = afterText
        }

    }
}