package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable

import kotlinx.coroutines.runBlocking


/**
 *  생산자의 맨 앞에 값을 추가
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.range(1, 5)

    val withStart = Observable.just(0)

    ob.startWith(withStart)
        .subscribe { println("received: $it") }
}
