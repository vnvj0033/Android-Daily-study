package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import java.lang.StringBuilder
import kotlin.coroutines.CoroutineContext

fun main() {
//    coroutineObject()
//    coroutineWorkerObject()
    threadLocalData()
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

/**
 *  coroutine의 경우 특정 코루틴 내부에서만 사용되는 local data를 지정할 수 있습니다.
 *  ThreadLocal에 set으로 main을 저장하지만 asContentElement에 launch를 지정하여 코루틴 스코프 내에서는 launch값을 얻음
 */
val threadLocal = ThreadLocal<String?>() // declare thread-local variable
private fun threadLocalData() = runBlocking {

    threadLocal.set("main")

    println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")

    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")


        // 스코프에서 값을 변경하려면 withContext를 사용
        threadLocal.set("main")
        withContext(threadLocal.asContextElement("foo")) {
            println(threadLocal.get()) // Prints "main"
            threadLocal.set("UI")
        }
        println(threadLocal.get()) // Prints "main", not "UI"
    }
    job.join()
    println ("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
}
