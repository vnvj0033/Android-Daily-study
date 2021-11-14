package com.example.mvctutorial.designpattern._15observer

fun main() {
    val button = Button()
    button.setOnClickListener {
        println("is clicked")
    }
    button.onClick()
}
