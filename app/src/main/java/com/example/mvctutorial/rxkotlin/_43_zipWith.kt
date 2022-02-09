package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable
import kotlinx.coroutines.runBlocking

/**
 * Observable에서 전달하는 값에 대해서 pair를 맞춰서 전달
 * 단 최소 갯수에 맞춰서 전달함
 * */
fun main() = runBlocking<Unit> {
    val ob1 = (1..3).toObservable()
    val ob2 = Observable.just("one", "two", "three", "four")

    println("------zip()------")

    Observable.zip(ob1, ob2) { a: Int, b: String -> "$a: $b" }
        .subscribe { println(it) }

    println("-----zipWith()-----")

    ob1.zipWith(ob2) { a: Int, b: String -> "$a: $b" }
        .subscribe { println(it) }

}
