package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking

/**
 * skipWhile : 한번 조건이 맞지 않으면 그 이후 나머지는 조건과 상관없이 모두 방출 됩니다.
 * */

fun main() = runBlocking<Unit> {

    Observable.range(1, 10)
        .skipWhile { it % 3 != 0 }
        .subscribe { println(it) }

}

