package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 *  Processor은 backpressure를 지원하는 Subject
 * */
fun main() = runBlocking {
    val connectableFlowable = Flowable.interval(100, TimeUnit.MILLISECONDS)

    val publishProcessor = PublishProcessor.create<Long>()
    publishProcessor.subscribe {
        println("processor 1 - $it")
    }

    delay(300) // 데이터를 전달하지 않음

    connectableFlowable.subscribe(publishProcessor)

    delay(300) // processor 1 데이터 전달

    publishProcessor.subscribe {
        println("processor 2 - $it")
    }

    delay(300) // processor 1, 2 데이터 전달
}
