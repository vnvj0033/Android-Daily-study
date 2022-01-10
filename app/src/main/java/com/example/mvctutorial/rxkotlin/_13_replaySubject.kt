package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.ReplaySubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/** ReplaySubject는 cold observable과 유사하게 등록 시점 이전값을 모두 전달받은후 새로 배출되는 값을 전달 받습니다. */
fun main() {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val subject = ReplaySubject.create<Long>()

    observable.subscribe(subject)
    runBlocking { delay(300) }

    subject.subscribe { println("1st: $it") } // 0, 1, 2
    runBlocking { delay(300) } // 3, 4 ,5

    subject.subscribe { println("2nd: $it") } // 0, 1, 2, 3, 4, 5
    runBlocking { delay(300) } // 6, 7, 8
}
