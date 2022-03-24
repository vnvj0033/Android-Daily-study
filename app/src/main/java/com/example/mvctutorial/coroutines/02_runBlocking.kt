package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

/**
 * runBlocking은 실행한 coroutineScope가 있으면 자신의 종료를 막는 코루틴 필드이다. runBlocking을 시작한 coroutineScope가 있으면 runBlocking이 종료되지 않는다.
 * */
fun main() = runBlocking{ // start main coroutine
    launch { // launch new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main coroutine continues here immediately
//    delay(2000L) // delaying for 2 seconds to keep JVM alive
}
