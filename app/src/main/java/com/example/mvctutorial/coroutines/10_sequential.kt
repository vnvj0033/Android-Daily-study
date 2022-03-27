package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
//    sequentialIsDefault()
//    concurrentUsingAsync()
    lazilyStartedSync()
}

/**
 * 기본적인 코루틴 필드 안의 작업은 동기를 지킨다.
 * */
private suspend fun sequentialIsDefault() = coroutineScope {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()

        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}

/**
 * async는 새로운 코루틴 필드를 만들고 return값을 반환한다. (Job을 return하지 않는다.)
 * */
private suspend fun concurrentUsingAsync() = coroutineScope {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }

        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

/**
 * completableFuture.supplyAsync를 사용하면 async를 지연하여 start를 만나야 시작할 수 있다.
 * */
private suspend fun lazilyStartedSync() = coroutineScope {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // some computation
        one.start() // start the first one
        two.start() // start the second one
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")

}

private suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}
