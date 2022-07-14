package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() {

}

/** 코루틴에서 스레드 풀 만들기 */
fun createCoroutineThreadPool() = runBlocking {
    val poolDispatcher = newFixedThreadPoolContext(3, "ThreadPool")
    val singleDispatcher = newSingleThreadContext("singleThread")
}

/** 안드로이드에서 Dispatcher */
fun androidDispatcher() = runBlocking {
    CoroutineScope(Dispatchers.Main)
    CoroutineScope(Dispatchers.IO)
    CoroutineScope(Dispatchers.Default)
}


