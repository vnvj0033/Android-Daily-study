package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
/**
 * flowable은 내부적으로 buffer(최대 128개)지원하여, 생산 속도를 제어
 * flowable을 구독하면 subscribe에서 전달
 * observeOn을 사용하면 subscribe이 쓰레드에서 실행되어 비동기 지원(전달과 수신의 비동기)
 * */
fun main() = runBlocking {

    val flowable = Flowable.range(1, 150)

    flowable.map {
        println("Mapping item $it")
        it * 1000
    }
        .observeOn(Schedulers.computation())
        .subscribe {
            println("Received item $it")
        }
    delay(1000)
}
