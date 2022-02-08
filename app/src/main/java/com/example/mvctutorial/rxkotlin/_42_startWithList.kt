package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val ob = Observable.range(5, 6)

    ob.startWith(Observable.just(1, 2, 3, 4))
        .subscribe { println("Received $it") }

}
