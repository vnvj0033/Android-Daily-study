package com.example.mvctutorial

import android.app.Application
import com.example.mvctutorial.dagger2.sandbox.di.DaggerAppComponent

class App: Application() {
    val appComponent = DaggerAppComponent.create()
}