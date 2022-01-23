package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 *  Throttling
 *   - 연속적인 emission을 수신측에서 다 처리할 수 없는 경우 특정 배출만 획득하고 나머지는 버리도록 하는 작업
 *
 *   sample or throttleLast는 정해진 단위 시간내에 배출된 마지막 값을 단위시간이 끝날때 마다 주기적으로 획득
 * */

fun main() = runBlocking {

    // 100ms 마다 데이터를 전달하는 observable
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val time = System.currentTimeMillis()

    // 200ms 마다 수신된 데이터의 마지막 값을 전달받는다.
    observable.sample(200, TimeUnit.MILLISECONDS).subscribe {
        println("Time: ${System.currentTimeMillis() - time} : $it")
    }
    delay(1111)

    /**
     * 수신 데이터 (전달 100ms, 수신 200ms, 받는 시간 1000ms)
     * 200ms : 0
     * 400ms : 2
     * 600ms : 4
     * 800ms : 6
     * 1000ms : 8
     * */
}
