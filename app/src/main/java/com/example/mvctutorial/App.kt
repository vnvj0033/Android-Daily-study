package com.example.mvctutorial

import android.app.Application
import com.example.mvctutorial.dagger2.sandbox.di.DaggerApplicationComponent

class App: Application() {
    val appComponent = DaggerApplicationComponent.create()
}