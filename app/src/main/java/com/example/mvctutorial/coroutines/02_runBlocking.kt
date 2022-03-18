package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking{ // start main coroutine
    GlobalScope.launch { // launch new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main coroutine continues here immediately
    delay(2000L) // delaying for 2 seconds to keep JVM alive
}
