package com.example.mvctutorial.designpattern._07builder

abstract class BluePrint {
    abstract fun setCpu()
    abstract fun setRam()
    abstract fun setStorage()
    abstract fun getComputer(): Computer
}