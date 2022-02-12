package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * concat: 선언된 순서를 보장하여 각 Observable을 이어 붙임, (Observable이 끝나야 다음 Observable이 방출)
 * */

fun main() = runBlocking<Unit> {
    val ob1 = Observable.just(1, 2)
        .map {
            runBlocking { delay(100) }
            it
        }

    val ob2 = Observable.just(3, 4)

    println("----- concat() ------")

    Observable.concat(ob1, ob2)
        .subscribe({ println(it) },  { println("error") }, { println("completed") })

    println("----- concatWith() ------")
    ob1.concatWith(ob2)
        .subscribe { println(it) }

    println("----- concatArray() ------")
    val ob3 = Observable.just(5, 6)
    val ob4 = Observable.just(7, 8)
    val ob5 = Observable.just(9, 10)

    Observable.concatArray(ob1, ob2, ob3, ob4, ob5)
        .subscribe { println(it) }

}
