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
 *  BackpressureStrategy.LATEST는 수신자가 처리중에 생산자로 부터 전달을 받으면 해당 데이터는 버림.
 *  단 마지막 데이터는 유지하여 onComplete 호출
 *  버퍼는 128개 사용, 128개 수신까지는 저장되어 처리.
 * */
fun main() = runBlocking {
    val flowable = Flowable.create<Int>({
        for (i in 1..200) {
            println("send item $i")
            it.onNext(i)
            runBlocking { delay(10) }
        }
        it.onComplete()
    }, BackpressureStrategy.LATEST)

    // runBlocking 블럭을 유지하기 위해 observing이 끝날때 까지 대기하기 위해서 사용한다.
    val waitingJob = launch {
        delay(Long.MAX_VALUE)
    }

    flowable.observeOn(Schedulers.computation()).subscribe(MySubscriber20(waitingJob))
}

class MySubscriber20(private val job: Job) : Subscriber<Int> {
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
        job.cancel()
    }

}
