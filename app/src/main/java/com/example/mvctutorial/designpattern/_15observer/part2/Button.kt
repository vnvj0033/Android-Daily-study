package com.example.mvctutorial.designpattern._15observer.part2

import java.util.*

class Button: Observable() {
    fun onClick() {
        setChanged()
        notifyObservers()
    }
}