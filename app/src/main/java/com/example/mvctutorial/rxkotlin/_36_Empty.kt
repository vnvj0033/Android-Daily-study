package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


/**
 *  defaultIfEmpty는 filter의 조건에 안맞을 경우
 *  switchIfEmpty observable의 조건을 추가로 비교해서 안 맞을 경우
 * */

fun main() = runBlocking<Unit> {
    // 1부터 10까지 100ms 간격으로 출력
    val ob = createSlowObservable_36(1)

    val elapsedTime1 = measureTimeMillis {
        ob.filter { it == 20 }
            .defaultIfEmpty(-1)
            .subscribe { println("received: $it") }
    }

    println("elapsed time1:$elapsedTime1")

    // 11부터 20까지 100ms 간격으로 출력
    val ob2 = createSlowObservable_36(11)

    val elapsedTime2 = measureTimeMillis {
        ob.filter { it == 20 }
            .switchIfEmpty(ob2).filter { it == 20 }
            .defaultIfEmpty(-1)
            .subscribe { println("received: $it") }
    }

    println("elapsed time2:$elapsedTime2")

}

private fun createSlowObservable_36(startNum: Int): Observable<Int> {
    val endNum = startNum + 10
    return Observable.create {
        for (i in startNum until endNum) {
            it.onNext(i)
            runBlocking { delay(100) }
        }
        it.onComplete()
    }
}
