package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * observeOn은 context를 switching 한다.
 * */

fun main() = runBlocking<Unit> {

    Observable.just(1)
        .observeOn(Schedulers.io())
        .map {
            println("mapping#1 - ${Thread.currentThread().name}")
            it
        }.observeOn(Schedulers.computation())
        .map {
            println("mapping#2 - ${Thread.currentThread().name}")
            it
        }.observeOn(Schedulers.single())
        .subscribe {
            println("subscribe $it - ${Thread.currentThread().name}")
        }

    delay(100)

}
