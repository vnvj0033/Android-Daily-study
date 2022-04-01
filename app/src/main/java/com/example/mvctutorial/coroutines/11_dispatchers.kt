package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() {
//    dispatchersAndThreads()
//    unconfinedAndConfinedDispatcher()
//    debuggingCoroutinesAndThreads()
//    jumpingBetweenThreads()
    jobInTheContext()
}

/**
 * 파라미터 없이 launch를 사용하면 부모 CoroutineScope의 context와 dispatcher를 상속.
 * Dispatchers.Unconfined는 main에서 시작하고 적정한 쓰레드에 할당
 * Dispatchers.Default는 GlobalScope에서 launch와 같다.
 * newSingleThreadContext()는 코루틴을 실행시킬 새로운 thread를 생성 (thread를 새로 띄우는 비용이 들며 release해야함)
 * */
private fun dispatchersAndThreads() = runBlocking {
    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking : I'm working in thread ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined : I'm working in thread ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
        println("Default : I'm working in thread ${Thread.currentThread().name}")
    }

    launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
}

/**
 * Dispatcher를 Unconfined로 설정하면, 해당 coroutine은 caller thread에서 시작
 * suspend되었다가 상태가 재시작 되면 적절한 thread에 재할당
 * UI 처럼 main thread에서만 수행되어야 하는 작업들은 unconfined로 지정하면 suspend시 지연 현상 발생
 * */
private fun unconfinedAndConfinedDispatcher() = runBlocking {
    launch(Dispatchers.Unconfined) {
        // not confined -- will work with main thread
        println("Unconfined : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined : After delay in thread ${Thread.currentThread().name}")
    }

    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}

/**
 * 코루틴은 suspend와 resume이 반복되면서 다른 thread로 변경될 수 있다.
 * -Dkotlinx.coroutines.debug를 JVM option에 추가하면 coroutine에 대한 정보도 로그로 남길 수 있다.
 * */
private fun debuggingCoroutinesAndThreads() = runBlocking {
    val a = async {
        println("[${Thread.currentThread().name}] I'm computing a piece of the answer")
        6
    }

    val b = async {
        println("[${Thread.currentThread().name}] I'm computing another piece of the answer")
        7
    }

    println("[${Thread.currentThread().name}] The answer is ${a.await() * b.await()}")
}

/**
 * use는 Closeable클래스에 예외상황에도 close를 보장
 * 하나의 coroutine이 어떻게 다른 thread에서 분리되어 실행되는지 예제
 * */
@OptIn(ObsoleteCoroutinesApi::class)
private fun jumpingBetweenThreads() {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                println("[${Thread.currentThread().name}] Started in ctx1")
                withContext(ctx2) {
                    println("[${Thread.currentThread().name}] Working in ctx2")
                }
                println("[${Thread.currentThread().name}] Back to ctx1")
            }
        }
    }
}

/**
 * Job은 context의 일부, coroutineContext[Job]을 통해 job을 코드안에서 추출 가능
 * 따라서 coroutineScope 내에서 Job 사용가능
 * */
private fun jobInTheContext() = runBlocking {
    println("My job is ${coroutineContext[Job]}")
}
