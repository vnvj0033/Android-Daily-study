package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() {
//    base1()
    base2()
}

private fun base1() {
    GlobalScope.launch { // launch new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

private fun base2() = runBlocking{
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")

}
