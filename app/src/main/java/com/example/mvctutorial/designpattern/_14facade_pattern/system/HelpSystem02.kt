package com.example.mvctutorial.designpattern._14facade_pattern.system

class HelpSystem02 {
    init {
        println("call constructor : " + javaClass.simpleName)
    }

    fun process() {
        println("call process : " + javaClass.simpleName)
    }
}