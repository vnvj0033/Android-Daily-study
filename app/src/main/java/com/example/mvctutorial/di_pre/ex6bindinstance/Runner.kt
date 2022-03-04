package com.example.mvctutorial.di_pre.ex6bindinstance

fun main() {
    val appComponent: AppComponent = DaggerAppComponent.builder().name("aa").build()
    println(appComponent.getApplication().name) // aa
}
