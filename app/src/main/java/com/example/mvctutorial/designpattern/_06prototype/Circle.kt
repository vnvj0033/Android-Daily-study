package com.example.mvctutorial.designpattern._06prototype

open class Circle(var x: Int, var y: Int, var r: Int): Shape() {

    fun copy(): Circle {
        return (clone() as Circle).apply {
            x += 1
            y += 1
        }
    }
}