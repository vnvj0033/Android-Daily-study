package com.example.mvctutorial.dagger2.sandbox.di

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SandBoxActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
        val userRepository: UserRepository = applicationGraph.repository()


    }
}