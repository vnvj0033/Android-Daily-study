package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

/**
 * skip : 조건에 따라서 전발하지 않음
 * */

fun main() = runBlocking<Unit> {
    val ob = Observable.intervalRange(1, 10, 0, 100, TimeUnit.MILLISECONDS)

    println("----- skip(count) ------")

    ob.skip(5)                              // 회수를 skip
        .blockingSubscribe { println(it) }

    println("----- skip(time) ------")

    ob.skip(300, TimeUnit.MILLISECONDS)     // 시간동안 skip
        .blockingSubscribe { println(it) }

}

