package com.example.mvctutorial.designpattern._01strategy

fun main() {
    val ainterface : AInterface = AInterfaceImpl()
    ainterface.funcA()

    val aObj = AObj(AInterfaceImpl())
    aObj.funcAA()
}
