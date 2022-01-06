package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/** connectable observer은 connect를 이후에도 구독되면 바로 실행  */
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

    // 3번 구독자 등록, observable 종료 전에 구독하여 실행됨
    connectableObservable.subscribe { println("3st subscriber: $it") }

    runBlocking { delay(300) }
}
