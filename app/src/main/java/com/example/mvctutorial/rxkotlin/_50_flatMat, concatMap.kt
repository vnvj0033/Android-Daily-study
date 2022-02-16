package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

/**
 * measureTimeMillis는 실행 시간을 반환
 * blockingSubscribe을 사용하면 실행한 쓰레드에서 실행 (동기적 처리로 로그 보기가 편함)
 * flatMap : 자신과 Observable을 전달 받는대로 merge하여 반환
 * concatMap : 자신과 Observable을 순서를 보장하여 merge
 * */

fun main() = runBlocking {

    val ob1 = Observable.range(1, 5)

    println ("------ flatMap() ------")
    val elapsedTime1 = measureTimeMillis {

        ob1.flatMap { getDelayedObservable(it) }
            .blockingSubscribe { println(it) }
    }

    println ("Elapsed Time:$elapsedTime1")


    println ("------ concatMap() ------")
    val elapsedTime2 = measureTimeMillis {

        ob1.concatMap { getDelayedObservable(it) }
            .blockingSubscribe { println(it) }
    }

    println ("Elapsed Time:$elapsedTime2")

}

//랜덤 delay를 갖는 observable을 반환
fun getDelayedObservable(value: Int): Observable<String> {
    val delay = Random().nextInt(100)

    return Observable.just(value)
        .map { "Delay:$delay - $it" }
        .delay(delay.toLong(), TimeUnit.MILLISECONDS)
}
