package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking

/**
 * retryUntil : retry 람다식과 유사, 람다 반환 조건으로 재시도 여부 판단
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.range(1, 10)
        .map {
            if (it == 5) throw Exception("Error!!!")
            else it
        }

    var retryCnt = 0
    ob.retryUntil {
        if (retryCnt == 3) {
            true
        } else {
            println("Retry!!!")
            retryCnt++
            false
        }
    } .subscribeBy(
        onNext = { println(it) },
        onError = { e -> println(e) }
    )

}
