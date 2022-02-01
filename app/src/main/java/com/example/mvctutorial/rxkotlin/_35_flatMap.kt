package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking


/**
 *  flatMap은 collection과 동일한 기능을 제공
 * */

fun main() = runBlocking<Unit> {
    val ob = Observable.range(1, 3)

    ob.flatMap { makeObserver_35(it) }
        .subscribeBy(
            onNext = { println(" item: $it") },
            onComplete = { println("complete") }
        )
}

fun makeObserver_35(seed: Int) = Observable.create<Int> {
    for (i in 0 until seed) {
        it.onNext(seed)
    }
    it.onComplete()
}
