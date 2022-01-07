package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/** PublishSubject는 구독된 시점부터 데이터를 전달받는다.  */
fun main() {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val subject = PublishSubject.create<Long>()

    runBlocking { delay(300) } // 실행 X

    observable.subscribe(subject)
    runBlocking { delay(300) } // 0, 1, 2

    subject.subscribe { println("1st: $it") }
    runBlocking { delay(300) } // 3, 4, 5

    subject.subscribe { println("2nd: $it") }
    runBlocking { delay(300) } // 6, 7, 8
}
