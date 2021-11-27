package com.example.mvctutorial.designpattern._07builder

fun main() {

    val factory = ComputerFactory()
    factory.print = Mac()
    val com = factory.makeComputer()

    val computer = ComputerBuilder
        .start()
        .setCpu("R5 3600")
        .setRam("32g")
        .setStorage("1T hdd")
        .build()


    println(com)
    println(computer)
}