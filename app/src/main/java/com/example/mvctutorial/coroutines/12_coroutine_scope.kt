package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * 코루틴을 객체화 하는 예제 (안드로이드 생명주기에 맞도록 설계)
 * */
fun main() = runBlocking {
    val activity = Activity()
    activity.create() // create an activity
    activity.doSomething() // run test function
    println("Launched coroutines")
    delay(500L) // delay for half a second
    println("Destroying activity!")
    activity.destroy() // cancels all coroutines
    delay(1000) // visually confirm that they don't work
}

class Activity : CoroutineScope {
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job // to be continued ...

    fun create() { job = Job() }
    fun destroy() { job.cancel() }
    // to be continued ...
    // class Activity continues

    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        repeat(10) { i ->
            launch {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, ... etc
                println("Coroutine $i is done")
            }
        }
    }
}