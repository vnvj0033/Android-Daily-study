package com.example.mvctutorial.designpattern.template

fun main() {

    val abstConnectHelper = ConcreteConnectHelper()
    println(abstConnectHelper.requestConnection("info"))

}
