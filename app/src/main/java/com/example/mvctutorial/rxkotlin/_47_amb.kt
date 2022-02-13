package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * amb: 가장 빠르게 시작하는 Observable만 사용하고 나머지 Observable의 방출은 버림
 * */

fun main() = runBlocking<Unit> {
    val ob1 = Observable.just(1, 2).map {
        runBlocking { delay(100) }
        it
    }

    val ob2 = Observable.just(3, 4)

    Observable.amb(listOf(ob1, ob2)).blockingSubscribe { println("amb: $it") }
    Observable.ambArray(ob1, ob2).blockingSubscribe { println("ambArray: $it") }

}
