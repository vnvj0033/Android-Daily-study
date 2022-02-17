package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

/**
 * switchMap : Observable을 비동기로 방출하고 합치나 이전 전달값이 다음 전달보다 느리면 버려짐
 * */

fun main() = runBlocking<Unit> {

    Observable.range(1, 10)
        .switchMap {
            if (it % 2 == 0) {
                Observable.just(it).delay(100, TimeUnit.MILLISECONDS)
            } else {
                Observable.just(it)
            }
        }.subscribe { println(it) }

}

