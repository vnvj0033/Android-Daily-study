package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable

import kotlinx.coroutines.runBlocking


/**
 *  sorted : 객체를 정렬하여 전달함 단 메모리 이슈 조심 OOM, ANR 이슈 가능성 존재
 * */

fun main() = runBlocking<Unit> {
    val ob = Observable.just(1, 3, 5, 7, 9, 2, 4, 6, 8, 10)
    val withStart = Observable.just(0)

    println("sort()")


    ob.startWith(withStart).sorted() // 오름차순 정렬
        .subscribe { print("$it, ") }

    println("")

    println ("comaprator sort()")

    ob.startWith(withStart)
        .sorted { num1, num2 -> if (num1 >= num2) -1 else 1 } // 내림차순 정렬
        .subscribe { print("$it, ") }

}
