package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking


/**
 *  take는 지정 갯수만큼 전달
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.range(1, 100)

    ob.cast(Number::class.java)
        .take(5)
        .subscribe { println(it) }

}
