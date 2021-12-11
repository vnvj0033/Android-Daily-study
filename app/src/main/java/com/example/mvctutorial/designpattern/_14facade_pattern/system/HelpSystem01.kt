package com.example.mvctutorial.designpattern._14facade_pattern.system

class HelpSystem01 {
    init {
        println("call constructor : " + javaClass.simpleName)
    }

    fun process() {
        println("call process : " + javaClass.simpleName)
    }
}