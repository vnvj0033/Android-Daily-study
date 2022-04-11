package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() {
//    exceptionPropagation()
    coroutineExceptionHandler()
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
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }

}

/**
 * CoroutineExceptionHandler를 이용하여 coroutine 내부의 기본 catch block으로 사용할 수 있다
 * 예제를 보면 launch에서 발생한 exception만 처리, async처럼 await()를 만나야 exception이 발생하는 경우에는 동작하지 않음
 * */
private fun coroutineExceptionHandler() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    val job = GlobalScope.launch(handler) { throw AssertionError() }
    val deferred = GlobalScope.async(handler) {
        throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
    }
    joinAll(job, deferred)
}