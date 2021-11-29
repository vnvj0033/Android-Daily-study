package com.example.mvctutorial.designpattern._09bidge

class SoundMCF: MorseCodeFunction {
    override fun dot() {
        println("b")
    }

    override fun dash() {
        println("B")
    }

    override fun space() {
        println(" ")
    }
}