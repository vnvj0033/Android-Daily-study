package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
//    cancellationIsCooperative()
//    closingResourcesWithFinally()
    withTimeoutOrNull()
}

suspend fun cancellationIsCooperative() = coroutineScope {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // computation loop, just wastes CPU
            // yield() // 여기에 추가하면 정상적으로 취소된다
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}


suspend fun closingResourcesWithFinally() = coroutineScope {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("I'm running finally")
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

// Job으로 cancel하지 않고, 특정 시간이후에 취소하도록 하려면 withTimeout을 사용
// withTimeoutOrNull은 타임아웃시 null 반환 정상 종료면 마지막 값을 반환
suspend fun withTimeoutOrNull() = coroutineScope {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // will get cancelled before it produces this result
    }
    println("Result is $result")
}