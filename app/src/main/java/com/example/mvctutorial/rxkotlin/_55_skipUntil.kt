package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 * skipUntil : Observable의 방출을 다른 Observable의 방출 시점으로
 * */

fun main() = runBlocking<Unit> {
    val ob1 = Observable.interval(100, TimeUnit.MILLISECONDS)
    val ob2 = Observable.timer(1, TimeUnit.SECONDS)

    ob1.skipUntil(ob2).subscribe { println(it) }

    delay(1500)

}

