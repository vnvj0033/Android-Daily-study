package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.kotlin.toObservable
import kotlinx.coroutines.runBlocking


/**
 *  distinct는 이전 출력값과 동일한 값이 배출되면 아이템을 걸러냄
 *  distictUntilChanged 연산자는 바로 직전 연산자와 동일하면 걸러냄
 * */

fun main() = runBlocking<Unit> {
    println("distict")

    listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6 ,1)
        .toObservable()
        .distinct()
        .subscribe { print("$it, ") }

    println("")
    println("distinctUntilChanged")

    listOf(1, 1, 1, 2, 3, 4, 5, 5, 5, 6, 1, 2, 3)
        .toObservable()
        .distinctUntilChanged()
        .subscribe { print("$it, ") }
}
