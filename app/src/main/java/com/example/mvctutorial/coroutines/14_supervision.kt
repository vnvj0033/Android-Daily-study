package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import java.io.IOException

fun main() {
    supervisionJob()
}

/**
 * SupervisorJob의 경우 아래 방향으로만 취소를 전파
 * 예제의 fisrtChild를 launch하면서 exception이 발생하므로 second child는 바로 취소
 * */
private fun supervisionJob() = runBlocking {
    val supervisor = SupervisorJob()
    try {
        with(CoroutineScope(coroutineContext + supervisor)) {
            val firstChild = launch(CoroutineExceptionHandler { _, exception -> println("caught $exception") }) {
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

