package com.example.mvctutorial.designpattern._05singleton

fun main() {
    val ssc = SoundSystemComponent

    SoundSystemComponent.volume = 5
    println(ssc.volume)

    SoundSystemComponent.volume = 10

    val ssc2 = SoundSystemComponent
    println(ssc2.volume)

    println(ssc == ssc2)
}