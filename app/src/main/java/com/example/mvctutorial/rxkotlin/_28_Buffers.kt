package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 *  Buffers는 조건에 따라서 배출되는 데이터를 묶어 collection 형태로 반환
 *  Time interval 특정 시간 간격으로 묶는것도 가능
 * */

fun main() = runBlocking {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS) // 100ms 마다 데이터를 발생
    val intervalTime = Observable.interval(200, TimeUnit.MILLISECONDS) // 전달은 200ms 마다 collection으로 전달

    val time = System.currentTimeMillis()

    observable.buffer(intervalTime)
        .subscribe { println("Time: ${System.currentTimeMillis() - time} : $it") }

    delay(1000)
}
