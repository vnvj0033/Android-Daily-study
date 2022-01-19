package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 *  BackpressureStrategy.MISSING은 backpressure을 사용하지 않음
 *  따라서 추가 동작을 위한 opearator 제공
 *  - onBackpressureBuffer()
 *  - onBackpressureDrop()
 *  - onBackpressureLatest()
 * */
fun main() = runBlocking {
    val flowable = Flowable.create<Int>({
        for (i in 1..200) {
            println("send item $i")
            it.onNext(i)
            runBlocking { delay(10) }
        }
        it.onComplete()
    }, BackpressureStrategy.MISSING)

    val waitingJob = launch {
        delay(Long.MAX_VALUE)
    }

    // onBackpressureBuffer()를 사용하면 BackpressureStrategy.BUFFER을 사용하는 것과 동일, 다만 버퍼 크기 지정 가능
    flowable.onBackpressureBuffer(20)
        .observeOn(Schedulers.computation())
        .subscribe(MySubscriber21(waitingJob))
}

class MySubscriber21(private val job: Job) : Subscriber<Int> {
    override fun onSubscribe(s: Subscription) {
        // 제한 200개
        s.request(200)
    }

    override fun onComplete() {
        println("onComplete()")

        // 완료되면 대기를 멈춘다.
        job.cancel()
    }

    override fun onNext(t: Int) {
        runBlocking { delay(100) }
        println("onNext(): $t - ${Thread.currentThread().name}")
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }

}
