package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking

/**
 *  toList : List 형으로 전달
 *  toSortedList : 정렬된 List형으로 전달
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.just(1, 4, 5, 8, 9, 3, 2, 10, 7, 6)

    ob.toList().subscribeBy { println(it) }

    ob.toSortedList().subscribeBy { println(it) }

}
