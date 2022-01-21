package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 *  ConnectableFlowable은 publish()를 사용해 connect호출까지 대기한다.(hot flowable)
 *  publish()가 없을 경우 등록하면 바로전송 시작
 * */
fun main() = runBlocking {
    val connectableFlowable = Flowable.interval(100, TimeUnit.MILLISECONDS).publish()

    connectableFlowable.subscribeOn(Schedulers.computation())
        .subscribe { println("Subscription 1: $it") }

    delay(500) // 데이터가 발생하지 않음

    connectableFlowable.connect()

    delay(500) // Subscription 1 데이터가 발생

    connectableFlowable.subscribeOn(Schedulers.io())
        .subscribe { println("Subscription 2: $it") }

    delay(500) // Subscription 1, 2 데이터 발생
}
