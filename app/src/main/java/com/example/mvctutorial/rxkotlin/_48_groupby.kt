package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking

/**
 * groupBy: Groupping하고, 결과로도 Observable을 반환
 * */

fun main() = runBlocking<Unit> {

    val ob1 = Observable.range(1, 10)
    ob1.groupBy { it % 2 == 0 }.subscribe { groupedObservable ->
        val key = groupedObservable.key
        groupedObservable.subscribe { println("key: $key value: $it") }
    }

}
