package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*

/**
 * launch는 코루틴 빌더이다. 새 coroutineScope를 생성하고 비동기 작동과 동시에 시작합니다.
 * delay는 일시 중단 기능이다. 특정 시간 동안 코루틴을 일시 중단 합니다.
 * GlobalScope는 application process의 코루틴 필드이며 전역으로 실행 가능한 coroutineScope다.
 * */
fun main() {
    GlobalScope.launch { // launch new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}
