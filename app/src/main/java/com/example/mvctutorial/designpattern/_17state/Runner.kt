package com.example.mvctutorial.designpattern._17state

fun main() {
    val light = Light()

    with(light) {
        off()
        off()
        off()

        on()
        on()
        on()

        off()
        on()
        off()
        on()
        off()
        on()
    }
}