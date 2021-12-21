package com.example.mvctutorial.designpattern._21command

import java.util.*

fun main() {

    val queue = PriorityQueue<Command>()

    queue.add(StringCommandPrint("ABCD"))
    queue.add(StringCommandPrint("ABC"))
    queue.add(StringCommandPrint("AB"))
    queue.add(StringCommandPrint("A"))

    for (command in queue) {
        command.execute()
    }
}