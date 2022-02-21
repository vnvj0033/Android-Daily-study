package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * takeLast : 마지막에 방출되는 조건에 맞는 데이터를 흭득 (skipLast과 반대)
 * */

fun main() = runBlocking<Unit> {
    val ob = Observable.create<Int> {
        it.onNext(1)
        it.onNext(2)
        it.onNext(3)
        it.onNext(4)
        it.onNext(5)
        it.onNext(6)
        it.onNext(7)
        it.onNext(8)
        it.onNext(9)
        it.onNext(10)

        runBlocking { delay(300) }

        it.onComplete()
    }

    val time = System.currentTimeMillis()

    val elapsedTime = measureTimeMillis {
        ob.takeLast(4)
            .subscribe { println("Emission Time:${System.currentTimeMillis() - time} - value:$it") }
    }

    println(elapsedTime)

}


