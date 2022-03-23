package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

/**
 * launch는 코루틴 빌더이다. 비동기 작동과 동시에 새 코루틴을 시작합니다.
 * delay는 일시 중단 기능이다. 특정 시간 동안 코루틴을 일시 중단 합니다.
 * runBlocking은 실행한 쓰레드의 종료를 막는 새 코루틴 필드이다. runBlocking을 시작한 쓰레드는 runBlocking이 종료되지 않으면 끝나지 않는다.
 * GlobalScope는 전역으로 실행 가능한 코루틴 필드이다.
 * */
fun main() {
//    base1()
    base2()
}

private fun base1() {
    GlobalScope.launch { // launch new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

private fun base2() = runBlocking{
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")

}
