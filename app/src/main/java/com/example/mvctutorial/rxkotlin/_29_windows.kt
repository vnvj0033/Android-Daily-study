package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 *  배출되는 형태를 묶어 observable로 반환
 * */

fun main() = runBlocking {

    val flowable = Flowable.interval(100, TimeUnit.MILLISECONDS)
//    flowable.window(300, TimeUnit.MILLISECONDS) // 시간으로 묶기
    flowable.window(5) // 갯수로 묶기
        .subscribe { longFlowable ->
            longFlowable.subscribe{
                print("$it ")
            }
            println()
        }

    delay(1000)
}
