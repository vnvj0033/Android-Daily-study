package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking


/**
 * retry : 에러발생시 다시시도, n번 횟수 또는 람다식(count, throwable -> boolean) 조건으로 재시도 여부 판단
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.range(1, 10)
        .map {
            if (it == 5) throw Exception("Error!!!")
            else it
        }

    ob.retry(2)
        .subscribeBy(
            onNext = { println(it) },
            onError = { e -> println(e) }
        )

    println("---- retry with condition ----")

    ob.retry { retryCnt, e ->
        println("retry cnt:$retryCnt")
        retryCnt <= 2
    }.subscribeBy(
        onNext = { println(it) },
        onError = { e -> println(e) }
    )

}


