package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking

/**
 * groupBy 숫자 분리
 * */

fun main() = runBlocking<Unit> {

    val ob1 = Observable.range(1, 30)
    val groupedObservable = ob1.groupBy { getKey(it) }

    groupedObservable.subscribe { groupedObservable ->
        groupedObservable.filter { groupedObservable.key == "small number" }
            .subscribe { println("${groupedObservable.key}: $it") }
    }

    groupedObservable.subscribe { groupedObservable ->
        groupedObservable.filter { groupedObservable.key == "middle number" }
            .subscribe { println("${groupedObservable.key}: $it") }
    }

    groupedObservable.subscribe { groupedObservable ->
        groupedObservable.filter { groupedObservable.key == "big number" }
            .subscribe { println("${groupedObservable.key}: $it") }
    }
}

fun getKey(num: Int): String = when {
    num < 10 -> "small number"
    num < 20 -> "middle number"
    else -> "big number"
}

