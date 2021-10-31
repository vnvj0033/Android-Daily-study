package com.example.mvctutorial.designpattern.singleton

fun main() {
    val ssc = SoundSystemComponent.getInstance()

    ssc.volume = 5
    println(ssc.volume)

    ssc.volume = 10

    val ssc2 = SoundSystemComponent.getInstance()
    println(ssc2.volume)

    println(ssc == ssc2)
}
