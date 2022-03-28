package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
//    sequentialIsDefault()
//    concurrentUsingAsync()
//    lazilyStartedSync()
//    asyncStyleFunctions()
    asyncStyleSaveMemoryLeak()
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

/**
 * GlobalScope을 이용해서 명시적으로 async 함수를 만들면 suspend function이 아니여
 * 어디서든 호출되어 사용할 수 있습니다.
 * */
private suspend fun asyncStyleFunctions() {
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}

/**
 * asyncStyleFunctions경우 await가 runBlocking 내부에 있어 runBlocking 이전 exception에 메모리 누수가 일어난다.
 * 다음의 경우 보다 메모리 누수에 안전하다.
 * */
private suspend fun asyncStyleSaveMemoryLeak() {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

private suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

private suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}


/**
 * GlobalScoped에서 async coroutine builder를 사용하여 명시적으로 비동기 형식의 함수를 만들수 있음
 * */
private fun somethingUsefulOneAsync() = GlobalScope.async { doSomethingUsefulOne() }

private fun somethingUsefulTwoAsync() = GlobalScope.async { doSomethingUsefulTwo() }

