package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import java.lang.StringBuilder
import kotlin.coroutines.CoroutineContext

fun main() {
//    coroutineObject()
    coroutineWorkerObject()
}

/**
 * 코루틴을 객체화 하는 예제 (안드로이드 생명주기에 맞도록 설계)
 * */
private fun coroutineObject() = runBlocking {
    val activity = Activity()
    activity.create() // create an activity
    activity.doSomething() // run test function
    println("Launched coroutines")
    delay(500L) // delay for half a second
    println("Destroying activity!")
    activity.destroy() // cancels all coroutines
    delay(1000) // visually confirm that they don't work

}

private class Activity : CoroutineScope {
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


/**
 * 코루틴을 워커로 사용하는 예제
 * */
private fun coroutineWorkerObject() = runBlocking {

    val worker = DbWorker()
    worker.onCreate()

    worker.updateScreen()

    delay(3000)

    worker.onDestroy()

    delay(1000)
}

private class DbWorker : CoroutineScope {
    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    fun onCreate() { job = SupervisorJob() }
    fun onDestroy() { job.cancel() }

    fun updateScreen() {
        launch(Dispatchers.Default) {
            val listData = getDataFormRoom()
            withContext(coroutineContext) {
                setAdapter(listData)
            }
        }
    }


    private suspend fun getDataFormRoom(): List<String> {
        delay(1500)
        return mutableListOf("apple", "banana", "tomato")
    }

    private suspend fun setAdapter(data: List<String>) {
        delay(1000)
        val stringBuilder = StringBuilder().apply {
            data.forEach { append("$it ") }
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndex)
        println(stringBuilder.toString())
    }
}
