package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 * take : 조건에 맞는 데이터를 흭득 (skip과 반대)
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.intervalRange(1, 10, 0, 100, TimeUnit.MILLISECONDS)

    println("----- take(count) ------")

    ob.take(5).blockingSubscribe { println(it) }

    println("----- take(time) ------")

    ob.take(300, TimeUnit.MILLISECONDS)
        .blockingSubscribe { println(it) }
}


