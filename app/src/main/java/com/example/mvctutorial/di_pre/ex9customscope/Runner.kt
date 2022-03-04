package com.example.mvctutorial.di_pre.ex9customscope

fun main() {
    val appComponent: AppComponent = DaggerAppComponent.create()
    val application1 = appComponent.getApplication()
    val application2 = appComponent.getApplication()
    println(application1 === application2) // true
}
