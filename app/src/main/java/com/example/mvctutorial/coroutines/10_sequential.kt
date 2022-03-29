package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
//    sequentialIsDefault()
//    concurrentUsingAsync()
//    lazilyStartedSync()
//    asyncStyleFunctions()
//    asyncStyleSaveMemoryLeak()
    scopeException()
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

/**
 * 코루틴 예외 상황에서 coroutineScope의 async가 취소됨을 볼수 있는 함수
 * */
private fun scopeException() {
    runBlocking {
        var value = 0
        try {
            value = failedConcurrentSum()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        } finally {
            println("Main fianlly, value = $value")
        }
    }
}

private suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async {
        println("first async")
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }

    val two = async {
        println("second async")
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } catch (e: CancellationException) {
            println("second child was cancelled")
            42
        }
    }

    val three = async {
        println("Third child throws an exception")
        throw ArithmeticException()
        42
    }

    one.await() + two.await() + three.await()
}

/**
 * async는 coroutineScope의 extention 함수.
 * 따라서 exception이 발생하면 해당 scope을 빠져나가면서 해당 coroutineScope에서 수행되었던 자식 coroutine 모두 취소
 * */
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

