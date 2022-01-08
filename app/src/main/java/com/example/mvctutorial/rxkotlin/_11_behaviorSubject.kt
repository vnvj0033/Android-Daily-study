package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/** BehaviorSubject는 이전 반출값인 값을 전달하고 시작한다. */
fun main() {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val subject = BehaviorSubject.create<Long>()

    observable.subscribe(subject)
    runBlocking { delay(300) } // 0, 1, 2

    subject.subscribe { println("1st: $it") }
    runBlocking { delay(300) } // 3, 4, 5

    subject.subscribe { println("2nd: $it") }
    subject.subscribe { println("3nd: $it") }
    runBlocking { delay(300) } // 6, 7, 8
}
