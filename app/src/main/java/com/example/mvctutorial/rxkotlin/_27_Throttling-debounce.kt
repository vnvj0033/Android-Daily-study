package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 *  Throttling
 *   - 연속적인 emission을 수신측에서 다 처리할 수 없는 경우 특정 배출만 획득하고 나머지는 버리도록 하는 작업
 *
 *   debounce는 정해진 단위시간 보다 더 긴 간격으로 배출되는 아이템만을 획득
 * */

fun main() = runBlocking {

    // 100ms 마다 데이터를 전달하는 observable
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val time = System.currentTimeMillis()

    // 90ms 보다 더 긴 간격으로 수신된 데이터의 값을 전달받는다.
    observable.debounce (90, TimeUnit.MILLISECONDS).subscribe {
        println("Time: ${System.currentTimeMillis() - time} : $it")
    }
    delay(1111)
}
