package com.example.mvctutorial.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * launch는 Job을 반환한다. Job을 이용하면 백그라운드 작업상태나 스케쥴을 확인할 수 있다.
 * join을 이용하여 작업 완료를 기다린다. join 역시 non-blocking 코드입니다.
 * */
fun main() = runBlocking {
    val job = GlobalScope.launch { // launch new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println ("Hello,")
    job.join() // wait until child coroutine completes
}
