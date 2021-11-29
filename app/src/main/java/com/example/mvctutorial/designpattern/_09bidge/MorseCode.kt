package com.example.mvctutorial.designpattern._09bidge

open class MorseCode(private val function: MorseCodeFunction) {

    fun dot() {
        function.dot()
    }

    fun dash() {
        function.dash()
    }

    fun space() {
        function.space()
    }
}