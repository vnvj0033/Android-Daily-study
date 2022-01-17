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
 *  BackpressureStrategy.DROP은 수신자가 처리중에 생산자로 부터 전달을 받으면 해당 데이터는 버립니다.
 *  단 기본 버퍼 128개는 사용하여 128개 수신까지는 저장되어 처리함니다.
 * */
fun main() = runBlocking {
    val flowable = Flowable.create<Int>({
        for (i in 1..200) {
            println("send item $i")
            it.onNext(i)
            runBlocking { delay(10) }
        }
        it.onComplete()
    }, BackpressureStrategy.DROP)

    // runBlocking 블럭을 유지하기 위해 observing이 끝날때 까지 대기하기 위해서 사용한다.
    val waitingJob = launch {
        delay(Long.MAX_VALUE)
    }

    flowable.observeOn(Schedulers.computation()).subscribe(MySubscriber19(waitingJob))
}

class MySubscriber19(private val job: Job) : Subscriber<Int> {
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
