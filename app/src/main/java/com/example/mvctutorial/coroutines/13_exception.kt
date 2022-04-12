package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() {
//    exceptionPropagation()
//    coroutineExceptionHandler()
//    cancellationAndExceptions()
    cancellationAndExceptions2()
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

/**
 * cancel시 발생하는 CancellationException은 어떠한 핸들링도 받지 않음
 * */
private fun cancellationAndExceptions() = runBlocking {
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            }
//            catch(e: CancellationException) { println("CancellationException catch") } // 없어도 exception으로 종료되지 않음
            finally {
                println("Child is cancelled")
            }
        }
        yield()
        println("Cancelling child")
        child.cancel()
        child.join()
        yield()
        println("Parent is not cancelled")
    }

    job.join()
}

/**
 * Coroutine은 취소를 제외한 다른 exception이 발생하면 부모의 corouitne까지 모두 취소
 * 자식 코루틴에서 exception이 발생되면 다른 자식 코루틴이 모두 취소된 이후에 부모에 의해서 exception이 handling됨
 * */
private fun cancellationAndExceptions2() = runBlocking {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    val job = GlobalScope.launch(handler) {
        launch { // the first child
            try {
                delay(Long.MAX_VALUE)
            } finally {
                withContext(NonCancellable) {
                    println("Children are cancelled, but exception is not handled until all children terminate")
                    delay(100)
                    println("The first child finished its non cancellable block")
                }
            }
        }

        launch { // the second child
            delay(10)
            println("Second child throws an exception")
            throw ArithmeticException()
        }
    }
    job.join()
    println("End runBlocking")
}
