package com.example.mvctutorial.designpattern._07builder


class ComputerFactory {
    lateinit var print: BluePrint

    fun makeComputer(): Computer {
        print.setCpu()
        print.setRam()
        print.setStorage()
        return print.getComputer()
    }
}