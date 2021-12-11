package com.example.mvctutorial.designpattern._14facade_pattern.system

class HelpSystem03 {
    init {
        println("call constructor : " + javaClass.simpleName)
    }

    fun process() {
        println("call process : " + javaClass.simpleName)
    }
}