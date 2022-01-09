package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.AsyncSubject

/** AsyncSubject는 마지막값만 전달 */
fun main() {
    val observable = Observable.just(1,2,3,4,5,6,7,8,9,10)
    val subject = AsyncSubject.create<Int>()

    observable.subscribe(subject)

    subject.subscribe { println("1st: $it") }
    subject.subscribe { println("2nd: $it") }
}
