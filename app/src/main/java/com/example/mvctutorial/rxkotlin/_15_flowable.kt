package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    runBlocking { delay(1000) }

    val observable = Observable.range(1, 10)
    observable.map {
        val str = "Mapping item $it"
        runBlocking { delay(100) }
        println("$str - ${Thread.currentThread().name}")
        str
    }
        .observeOn(Schedulers.computation())
        .subscribe {
            println("Received $it - ${Thread.currentThread().name}")
            runBlocking { delay(200) }
        }
    delay(1000)
}
