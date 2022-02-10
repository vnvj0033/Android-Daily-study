package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 * 두개 이상의 Observable(Flowable)을 병합하여 출력
 * 두개의 Observable이 전달을 해야 combineLatest값을 전달 그 후는 Observable이 전달 때마다 값을 전달
 *
 * BiFunction는 다수의 primitive 변수를 하나로 반환
 * */

fun main() = runBlocking {

    val ob1 = Observable.interval(100, TimeUnit.MILLISECONDS)
    val ob2 = Observable.interval(250, TimeUnit.MILLISECONDS)

    val time = System.currentTimeMillis()

    ob1.subscribe { println("ob1: $it") }
    ob2.subscribe { println("ob2: $it") }

    Observable.combineLatest(ob1, ob2,
        BiFunction { a, b ->
            "Time:${System.currentTimeMillis() - time} ob1:$a ob2:$b"
        }
    ).subscribe { println(it) }

    delay(500)

}
