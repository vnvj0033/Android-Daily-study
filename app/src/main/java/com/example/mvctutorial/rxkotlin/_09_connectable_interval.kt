package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    val connectableObservable = Observable.interval(100, TimeUnit.MILLISECONDS).publish()

    // 1번 구독자 등록
    connectableObservable.subscribe { println("1st subscriber: $it") }

    // 2번 구독자 등록
    connectableObservable.map { "2st subscriber: $it" }
        .subscribe { println(it) }

    // 연결 시작
    connectableObservable.connect()

    runBlocking { delay(300) }

    // 3번 구독자 등록
    connectableObservable.subscribe { println("3st subscriber: $it") }

    runBlocking { delay(300) }
}
