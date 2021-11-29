package com.example.mvctutorial.designpattern._09bidge

class PrintMorseCode(function: MorseCodeFunction): MorseCode(function) {

    fun g(): PrintMorseCode {
        dash()
        dash()
        dot()
        space()
        return this
    }

    fun a(): PrintMorseCode {
        dot()
        dash()
        space()
        return this
    }

    fun r(): PrintMorseCode {
        dot()
        dash()
        dot()
        space()
        return this
    }

    fun m(): PrintMorseCode {
        dash()
        dash()
        space()
        return this
    }
}