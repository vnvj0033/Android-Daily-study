package com.example.mvctutorial.designpattern._10composite

class File(name: String): Component(name) {

    private val description = ""

    fun readFile() {
        println(description)
    }

}