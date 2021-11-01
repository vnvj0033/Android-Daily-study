package com.example.mvctutorial.designpattern._01strategy.ex

fun main() {
    val ainterface : Ainterface =
        AinterfaceImpl()

    ainterface.funcA()

    val aObj = AObj()
    aObj.funcAA()
}