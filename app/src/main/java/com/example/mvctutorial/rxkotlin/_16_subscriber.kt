package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 *  Subscriber은 Flowable이 Observer 대신 backpressure를 지원하여 사용
 *  사용은 Observer과 같지만 request를 조절하여 수신 데이터 조절
 *  */
fun main() = runBlocking {
    Flowable.range(1, 150).map {
        println("Mapping item $it - ${Thread.currentThread().name}")
        it
    }.observeOn(Schedulers.computation())
        .subscribe(MySubscriber16())
    delay(5000)

}

class MySubscriber16 : Subscriber<Int> {
    override fun onSubscribe(s: Subscription) {

        // request를 조절하여 전달 받는 수를 조정 OnComplete 호출 X
        s.request(5)
//        s.request(Long.MAX_VALUE)
    }

    override fun onComplete() = println("onComplete()")

    override fun onNext(t: Int) {
        runBlocking { delay(1) }
        println("onNext(): $t - ${Thread.currentThread().name}")
    }

    override fun onError(e: Throwable) = e.printStackTrace()
}