package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

fun main() {
    dispatchersAndThreads()
}

/**
 * 파라미터 없이 launch를 사용하면 부모 CoroutineScope의 context와 dispatcher를 상속.
 * Dispatchers.Unconfined는 main에서 실행
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

