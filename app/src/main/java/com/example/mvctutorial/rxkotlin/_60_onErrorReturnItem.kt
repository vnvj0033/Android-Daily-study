package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 * onErrorReturnItem : 에러를 다른 아이템으로 반환
 * onErrorReturn : 에러 발생시 람다식을 반환
 * */

fun main() = runBlocking<Unit> {
    Observable.range(1, 10)
        .map {
            if (it == 5) {
                throw Exception("Error!!!")
            } else {
                it
            }
        }
        .onErrorReturnItem(-1)
//        .onErrorReturn { e: Throwable -> -1 }
        .subscribeBy(
            onNext = { println(it) },
            onError = { e -> println(e) }
        )
}


