package com.example.mvctutorial.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    sharedMutableStateAndConcurrency()
}

/**
 * 공유된 자원을 건드리기위한 multi thread 동시성을 제어할 필요가있다.
 * 코루틴의 특수성으로 인해 아래의 경우는 GlobalScope에서 실행 하였으나 동기를 보장 받지 못 함
 * */
@Volatile
var counter = 0
private fun sharedMutableStateAndConcurrency() = runBlocking {

    GlobalScope.massiveRun {
        counter++
    }
    println("Counter = $counter")
}

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100 // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        val jobs = List(n) {
            launch {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} actions in $time ms")
}
