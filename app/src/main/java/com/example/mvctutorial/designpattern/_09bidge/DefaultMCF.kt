package com.example.mvctutorial.designpattern._09bidge

class DefaultMCF: MorseCodeFunction {
    override fun dot() {
        println(".")
    }

    override fun dash() {
        println("-")
    }

    override fun space() {
        println(" ")
    }
}