package com.example.mvctutorial.di.di_pre.ex6bindinstance

fun main() {
    val appComponent: AppComponent = DaggerAppComponent.builder().name("q23123123").build()
    println(appComponent.getApplication().toString()) // aa
}
