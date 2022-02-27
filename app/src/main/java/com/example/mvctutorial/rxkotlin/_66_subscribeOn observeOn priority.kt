package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * subscribeOn은 subscribe의 Observer 실행 쓰레드를 변경한다.
 * observeOn은 Observable의 context(대표적으로 실행 쓰레드)를 switching 한다.
 * */

fun main() = runBlocking<Unit> {
    MixedSubscribeOnAndObserveOn()
}


/**
 * Observable의 생산은 io Scheduler, 수신은 single Scheduler에서 하도록 설정
 * */
private suspend fun MixedSubscribeOnAndObserveOn() {
    println("start!")

    val ob = Observable.just(1)

    ob.subscribeOn(Schedulers.io())
        .map {
            println("processing in ${Thread.currentThread().name}")
            it
        }
        .observeOn(Schedulers.single())
        .subscribe { println("subscribed: $it - ${Thread.currentThread().name}") }

    delay(100)

    println("end!")
}
