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
//    MixedSubscribeOnAndObserveOn()
//    observeOnUnderSubscriptOn()
//    subscribeOnMultipleTimes()
    isTheSubscribeOnLocationImportant()
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

/**
 * subscribeOn이 두번 쓰였지만 먼저 선언된 Schedulers.io()가 동작함 (따라서 Thread는 cached Thread)
 * observeOn 아래에 subscribeOn이 선언되었지만 subscribe 블럭내 동작은 observeOn의 영향을 받습니다.
 * */
private suspend fun observeOnUnderSubscriptOn() {
    println("start!")

    val ob = Observable.just(1)

    ob.subscribeOn(Schedulers.io())
        .map {
            println("processing in ${Thread.currentThread().name}")
            it
        }.observeOn(Schedulers.single())
        .subscribeOn(Schedulers.computation())
        .subscribe { println("subscribed: $it - ${Thread.currentThread().name}") }

    delay(100)

    println("end!")
}

/**
 * subscribeOn을 중복해서 사용하면 먼저 선언된 Scheduler로 동작함
 * */
private suspend fun subscribeOnMultipleTimes() {
    println("start!")

    val ob = Observable.just(1)

    ob.subscribeOn(Schedulers.io())
        .map {
            println("processing in ${Thread.currentThread().name}")
            it
        }
        .subscribeOn(Schedulers.computation())
        .subscribe { println("subscribed: $it - ${Thread.currentThread().name}") }

    delay(100)

    println("end!")
}

private suspend fun isTheSubscribeOnLocationImportant() {
    println("start!")

    val ob = Observable.just(1)

    ob.map {
        println("processing in ${Thread.currentThread().name}")
        it
    }.observeOn(Schedulers.single())
        .subscribeOn(Schedulers.computation())
        .subscribe { println("subscribed: $it - ${Thread.currentThread().name}") }

    delay(100)

    println("end!")

}