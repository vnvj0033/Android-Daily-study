package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 *  Throttling
 *   - 연속적인 emission을 수신측에서 다 처리할 수 없는 경우 특정 배출만 획득하고 나머지는 버리도록 하는 작업입니다.
 *   sample or throttleLast는 정해진 단위 시간내에 배출된 마지막 값을 단위시간이 끝날때 마다 주기적으로 획득합니다.
 * */
fun main() = runBlocking {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val time = System.currentTimeMillis()
    observable.sample(200, TimeUnit.MILLISECONDS).subscribe {
        println("Time: ${System.currentTimeMillis() - time} : $it")
    }
    delay (1111)
}
