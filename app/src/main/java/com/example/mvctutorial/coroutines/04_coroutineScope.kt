package com.example.mvctutorial.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking#2")
    }

    coroutineScope { // Creates a new coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch#3")
        }

        delay(100L)
        println("Task from coroutine scope#1") // This line will be printed before nested launch
    }
    println("Coroutine scope is over#4") // This line is not printed until nested launch completes
}
