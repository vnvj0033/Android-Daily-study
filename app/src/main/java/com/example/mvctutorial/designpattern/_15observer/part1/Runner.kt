package com.example.mvctutorial.designpattern._15observer.part1


fun main() {
    val button = Button()
    button.onClickListener = object : Button.OnClickListener {
        override fun onClick(button: Button) {
            println("button click")
        }

    }
    button.onClick()
}