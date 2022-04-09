package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() {
    exceptionPropagation()
}

/**
 * coroutine의 예외는 propagation 그리고 exposing으로 나눈다.
 * launch의 경우 propagation 즉, 실행 즉시 외부로 예외를 전달한다.
 * async의 경우 exposing, await를 만나면 예외를 전달한다.
 * */
private fun exceptionPropagation() = runBlocking {
    val job = GlobalScope.launch {
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
    }
    job.join()
    println("Joined failed job")
    val deferred = GlobalScope.async {
        println("Throwing exception from async")
        throw ArithmeticException() // Nothing is printed, relying on user to call await
    }
    try {
        deferred.await()
        println ("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }

}