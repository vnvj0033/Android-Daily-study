package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking


/**
 * onErrorResumeNext : 에러발생시 Observable을 구독
 * */

fun main() = runBlocking<Unit> {
    Observable.range(1, 10)
        .map { if (it == 5) { throw Exception("Error!!!") } else { it } }
        .onErrorResumeNext {
            Observable.just(30, 20, 10)
        }
        .subscribeBy(
            onNext = { println(it) },
            onError = { e -> println(e) }
        )

}


