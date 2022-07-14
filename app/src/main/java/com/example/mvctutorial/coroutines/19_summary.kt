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

/** launch는 Job을 반환 */
fun launch() = runBlocking {
    val job: Job = CoroutineScope(Dispatchers.Main).launch {  }
}

/** async는 Deferred(갑을 포함)를 반환 */
fun async() = CoroutineScope(Dispatchers.Main).launch {
    val deferredInt : Deferred<Int> = async { 1 }

    val value = deferredInt.await() // 1
}