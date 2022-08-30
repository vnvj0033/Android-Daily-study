package com.example.mvctutorial.dagger2.sandbox

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvctutorial.App
import javax.inject.Inject

class SandBoxActivity: AppCompatActivity() {

    @Inject lateinit var model: SandBoxModel

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState, persistentState)

        model

    }
}