package com.example.mvctutorial.designpattern._06prototype


fun main() {
    val circle1 = Circle(1,1,1)


    val circle2 = circle1.copy()

    println("${circle1.x}, ${circle1.y}, ${circle1.r}")
    println("${circle2.x}, ${circle2.y}, ${circle2.r}")
}