package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * skipLast : 뒤에서 갯수만큼 제외하고 전달
 * 단 모두 전달 가능하도록 마지막값이 확인되면 전달한다.
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
        ob.skipLast(3)
            .subscribe { println("Emission Time:${System.currentTimeMillis() - time} - value:$it") }
    }

    println(elapsedTime)

}

