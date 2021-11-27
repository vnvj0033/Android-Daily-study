package com.example.mvctutorial.designpattern._07builder

class Mac: BluePrint() {

    private var computer = Computer("", "", "")

    override fun setCpu() {
        computer.cpu = "m1 pro max"
    }

    override fun setRam() {
        computer.ram = "32g"
    }

    override fun setStorage() {
        computer.storage = "1T ssd"
    }

    override fun getComputer(): Computer {
        return computer
    }
}