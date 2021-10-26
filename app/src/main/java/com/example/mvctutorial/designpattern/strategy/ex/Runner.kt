package com.example.mvctutorial.designpattern.strategy.ex

fun main() {
    val ainterface : Ainterface =
        AinterfaceImpl()

    ainterface.funcA()

    val aObj = AObj()
    aObj.funcAA()
}