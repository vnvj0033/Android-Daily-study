package com.example.mvctutorial.di

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvctutorial.databinding.ActivityDiBinding
import com.example.mvctutorial.di.student.Student

class DIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val person = Student()




    }
}