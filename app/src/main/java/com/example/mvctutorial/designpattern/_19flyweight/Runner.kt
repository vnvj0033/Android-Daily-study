package com.example.mvctutorial.designpattern._19flyweight

fun main() {
    val flyweightFactory = FlyweightFactory()

    val flyweight = flyweightFactory.getFlyweight("A")

    println(flyweight)

    flyweightFactory.getFlyweight("A")

    println(flyweight)
}
