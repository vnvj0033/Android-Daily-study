package com.example.mvctutorial.designpattern._21command

import java.util.*

fun main() {

    val list = LinkedList<Command>()

    list.add(object :Command {
        override fun execute() {
            println("작업 1")
        }
    })
    list.add(object :Command {
        override fun execute() {
            println("작업 2")
        }
    })
    list.add(object :Command {
        override fun execute() {
            println("작업 3")
        }
    })

    for (command in list){
        command.execute()
    }
}
