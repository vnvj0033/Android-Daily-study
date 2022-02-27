package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * subscribeOn은 subscribe의 Observer 실행 쓰레드를 변경한다.
 * observeOn은 Observable의 context(대표적으로 실행 쓰레드)를 switching 한다.
 * */

fun main() = runBlocking<Unit> {
    observeOn()
//    subscribeOn()
}

suspend fun observeOn() {

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
        .subscribe { println("subscribe $it - ${Thread.currentThread().name}") }

    delay(100)

}

suspend fun subscribeOn() {
    Observable.range(1, 3)
        .map {
            println("mapping - ${Thread.currentThread().name}")
            it
        }
        .subscribeOn(Schedulers.io())
        .subscribe { println("subscribe $it - ${Thread.currentThread().name}") }

    delay(100)
}
