package com.example.mvctutorial.designpattern._15observer.part1

import com.example.mvctutorial.designpattern._15observer.part1.Button

fun main() {
    val button = Button()
    button.setOnClickListener {
        println("is clicked")
    }
    button.onClick()
}
