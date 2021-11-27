package com.example.mvctutorial.designpattern._07builder


class ComputerBuilder {
    companion object {
        fun start(): ComputerBuilder {
            return ComputerBuilder()
        }
    }

    private val computer = Computer("", "", "")

    fun setCpu(cpu: String): ComputerBuilder {
        computer.cpu = cpu
        return this
    }

    fun setRam(ram: String): ComputerBuilder {
        computer.ram = ram
        return this
    }

    fun setStorage(storage: String): ComputerBuilder {
        computer.storage = storage
        return this
    }

    fun build(): Computer {
        return computer
    }

}
