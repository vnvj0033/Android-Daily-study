package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 * takeUntil : Observable의 방출을 다른 Observable의 방출 이전까지만
 * */

fun main() = runBlocking<Unit> {
    val ob1 = Observable.interval(100, TimeUnit.MILLISECONDS)
    val ob2 = Observable.timer(1, TimeUnit.SECONDS)

    ob1.takeUntil(ob2)
        .subscribe { println(it) }

    delay(1500)

}


