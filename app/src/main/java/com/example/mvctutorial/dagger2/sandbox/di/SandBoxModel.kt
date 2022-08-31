package com.example.mvctutorial.dagger2.sandbox.di

import android.util.Log
import javax.inject.Inject

class SandBoxModel @Inject constructor() {
    init {
        Log.d("testsyyoo", "init model")
    }

    val data = "123123"
}