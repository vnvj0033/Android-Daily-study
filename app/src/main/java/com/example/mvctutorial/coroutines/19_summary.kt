package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() {
    lazy()
}

/** 코루틴에서 스레드 풀 만들기 */
fun createCoroutineThreadPool() = runBlocking {
    val poolDispatcher = newFixedThreadPoolContext(3, "ThreadPool")
    val singleDispatcher = newSingleThreadContext("singleThread")
}

/** 안드로이드에서 Dispatcher */
fun androidDispatcher() = runBlocking {
    CoroutineScope(Dispatchers.Main)
    CoroutineScope(Dispatchers.IO)
    CoroutineScope(Dispatchers.Default)
}

/** launch는 Job을 반환 */
fun launch() = runBlocking {
    val job: Job = CoroutineScope(Dispatchers.Main).launch {  }
}

/** async는 Deferred(갑을 포함)를 반환 */
fun async() = runBlocking {
    val deferredInt : Deferred<Int> = async { 1 }

    val value = deferredInt.await() // 1
}

/** 하나의 쓰레드에서 코루틴 스코프에 따른 순서 */
fun progress() {
    val job1 = CoroutineScope(Dispatchers.IO).async {
        println("시작 job1")
        (1..10000).sortedByDescending { it }
    }

    val job2 = CoroutineScope(Dispatchers.Default).launch {
        println("시작 job2")
        job1.await()

        println("완료 job2")
    }

    val job3 = CoroutineScope(Dispatchers.Default).launch {
        println("시작 job3")
    }
}
/*
job1, job2, job3 동시 시작
job2 await 일시 중지 job1, job3 작업 진행
job3 job1 완료
job2에 await 값 받아 작업 진행
 */

/** 코루틴 일시정지는 코루틴 스코프 안에서 가능 suspend를 사용하면 코루틴 스코프에서만 사용할 수 있다 */
suspend fun suspendFun() {
    val job = CoroutineScope(Dispatchers.IO).async {
        (1..10000).sortedByDescending { it }
    }

    CoroutineScope(Dispatchers.Default).launch {
        job.await()
        println("끝")
    }
}

/** 코루틴은 start = CoroutineStart.LAZY를 사용해 지연 가능  */
fun lazy() {
    val job = CoroutineScope(Dispatchers.IO).launch(start = CoroutineStart.LAZY) {
        println("시작")
    }

    job.start()

    Thread.sleep(1000)
}