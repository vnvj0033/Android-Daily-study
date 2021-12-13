package com.example.mvctutorial.designpattern._16mediator

import com.example.mvctutorial.designpattern._16mediator.java.ChatColleague
import com.example.mvctutorial.designpattern._16mediator.java.ChatMediator

fun main() {
    val mediator =
        ChatMediator()

    val colleague1 =
        ChatColleague()
    val colleague2 =
        ChatColleague()
    val colleague3 =
        ChatColleague()

    colleague1.join(mediator)
    colleague2.join(mediator)
    colleague3.join(mediator)

    colleague1.sendData("AAA")
    colleague2.sendData("BBB")
    colleague3.sendData("CCC")
}