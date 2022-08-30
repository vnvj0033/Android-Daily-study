package com.example.mvctutorial

import android.app.Application
import com.example.mvctutorial.dagger2.sandbox.DaggerAppComponent

class App: Application() {
    val appComponent = DaggerAppComponent.create()
}