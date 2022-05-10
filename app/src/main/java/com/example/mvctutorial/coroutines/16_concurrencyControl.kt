package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main() {
//    sharedMutableStateAndConcurrency()
//    thread_safeDataStructures()
//    threadConfinementFineGained()
    threadConfinementCoarse_gained()
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

private suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
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

/**
 * 가장 간단한 해결 방법은 AtomicClass를 이용한다.
 * AtomicInteger 클래스는 멀티쓰레드 환경에서 동시성을 보장
 * 하지만 보다 복잡한 상태를 갖거나 복잡한 연산을 갖는경우에는 Atomic가지고는 충분하지 않음
 * */
val atomicInteger = AtomicInteger()
private fun thread_safeDataStructures() = runBlocking {
    GlobalScope.massiveRun {
        atomicInteger.incrementAndGet()
    }
    println ("Counter = ${atomicInteger.get()}")
}


/**
 * 상태를 관리하는 별도의 코루틴 필드에서 작업을 진행하여 동시성 보장 (UI 쓰레드)
 * 출력은 보장하나 연산이 느림
 * */
private val counterContext = newSingleThreadContext("CounterContext")
private fun threadConfinementFineGained() = runBlocking {
    GlobalScope.massiveRun {
        withContext(counterContext) {
            counter++
        }
    }
    println("Counter = $counter")
}

/**
 * 코루틴 스코프에 컨텍스트를 주입하여 작업 각기 다른 쓰레드에서 시작하는 작업의 동기를 맞춤
 * */
private fun threadConfinementCoarse_gained() = runBlocking {
    CoroutineScope(coroutineContext).massiveRun {
        counter++
    }
    println("Counter = $counter")
}