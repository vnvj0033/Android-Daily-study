package com.example.mvctutorial.di.di_pre.ex7nonscope

fun main() {
    val appComponent: AppComponent = DaggerAppComponent.create()
    val application1 = appComponent.getApplication()
    val application2 = appComponent.getApplication()
    println (application1 === application2) // false
}
