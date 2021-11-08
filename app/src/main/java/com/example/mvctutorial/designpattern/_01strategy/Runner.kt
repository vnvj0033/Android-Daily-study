package com.example.mvctutorial.designpattern._01strategy

import com.example.mvctutorial.designpattern._01strategy.AObj
import com.example.mvctutorial.designpattern._01strategy.Ainterface
import com.example.mvctutorial.designpattern._01strategy.AinterfaceImpl

fun main() {
    val ainterface : Ainterface =
        AinterfaceImpl()

    ainterface.funcA()

    val aObj = AObj()
    aObj.funcAA()
}