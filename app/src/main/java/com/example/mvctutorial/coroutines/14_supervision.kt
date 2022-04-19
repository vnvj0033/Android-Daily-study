package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import java.io.IOException

fun main() {
//    supervisionJob()
//    supervisionScope()
//    supervisionScope2()
//    exceptionsInSupervisedCoroutines()
    summery()
}

/**
 * SupervisorJob의 경우 아래 방향으로만 취소를 전파
 * 예제의 fisrtChild를 launch하면서 exception이 발생하므로 second child는 바로 취소
 * */
private fun supervisionJob() = runBlocking {
    val supervisor = SupervisorJob()
    try {
        with(CoroutineScope(coroutineContext + supervisor)) {
            val firstChild =
                launch(CoroutineExceptionHandler { _, exception -> println("caught $exception") }) {
                    println("First child is failing")
                    throw AssertionError("First child is cancelled")
                }

            // launch the second child
            val secondChild = launch {
                firstChild.join()
                // Cancellation of the first child is not propagated to the second child
                println("First child is cancelled: ${firstChild.isCancelled}, but second one is still active")
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    // But cancellation of the supervisor is propagated
                    println("Second child is cancelled because supervisor is cancelled")
                }
            }

            // wait until the first child fails & completes
            firstChild.join()
            println("Cancelling supervisor")
            supervisor.cancel()
            secondChild.join()
        }
    } catch (e: CancellationException) {
        println("CoroutineScope is cancelled!")
    }
}


/**
 * supervisorScope를 사용하면 부모로 취소를 전달하지 않음
 * */
private fun supervisionScope() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    println("Child is sleeping")
                    delay(Long.MAX_VALUE)
                } finally {
                    println("Child is cancelled")
                }
            }
            // Give our child a chance to execute and print using yield
            yield()
            println("Throwing exception from scope")
            throw AssertionError()
        }
    } catch (e: AssertionError) {
        println("Caught assertion error")
    }
}

/**
 * 해당 예제는 자식에서 예외를 처리해야 하지만 하지 않아서 프로세스가 죽는다.
 * */
private fun supervisionScope2() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    println("Child is sleeping")
                    delay (Long.MAX_VALUE)
                } finally {
                    println("Child is cancelled")
                }
            }

            val child2 = launch {
                println("Throwing exception from scope")
                throw AssertionError()
            }
        }
    } catch (e: AssertionError) {
        println("Caught assertion error")
    }
}

/**
 * supervisorScope을 사용하는 경우 필요에 따서 각 자식 coroutine에 exception handler를 달아줘야함
 * */
private fun exceptionsInSupervisedCoroutines() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    try {
        supervisorScope {
            val child = launch(handler) {
                println("Child throws an exception")
                throw AssertionError() }
            println("Scope is completing")
        }
        println("Scope is completed")
    } catch(e: Exception) {
        println("Exception happen!")
    }
}


private fun summery() = runBlocking {
    val supervisorJob = SupervisorJob()

    val handler = CoroutineExceptionHandler { _, exception -> println("Caught $exception") }

    // case #1: supervisorJob으로 인해 process가 죽지않고 exception은 위로 전달해 준다.
    // 따라서 정상적으로 handler가 동작한다.
    launch(handler) {
        launch {
            launch(supervisorJob) { throw IOException() }
        }
    }

    // case #2: supervisorJob으로 인해 process가 죽지않고 exception은 handler에서 바로 처리한다.
    launch {
        launch {
            launch(supervisorJob + handler) { throw IOException() }
        }
    }

    // case #3: exception이 발생한 상태에서는 supervisorJob과는 상관 없으므로 handler는 동작하지 못한다.
    // 단 supervisorJob까지 exception이 올라오면 더이상 위로 올라가는걸 막는다.
    launch(supervisorJob) {
        launch {
            launch(handler) { throw IOException() }
        }
    }

}