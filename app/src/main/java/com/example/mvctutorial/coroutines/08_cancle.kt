package com.example.mvctutorial.coroutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Job을 통해서 코루틴을 cancle 할 수 있다.
 * join을 사용하면 코루틴 필드의 작동를 끝까지 기다린다.
 * */
fun main() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // waits for job's completion
//    job.cancelAndJoin() //
    delay(1000)
    println("main: Now I can quit.")
//    delay(1300L) // delay a bit
}

