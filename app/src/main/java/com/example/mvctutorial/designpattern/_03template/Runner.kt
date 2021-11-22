package com.example.mvctutorial.designpattern._03template

fun main() {

    val abstConnectHelper = ConcreteConnectHelper()
    println(abstConnectHelper.requestConnection("info"))

}