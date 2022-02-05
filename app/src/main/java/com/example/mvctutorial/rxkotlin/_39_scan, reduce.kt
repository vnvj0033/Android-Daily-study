package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay

import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit


/**
 *  scan : 방출시점 직전 이전값을 같이 넘겨 받음
 *  reduce : 방출 이전 값과 누적하여 연산적용하여 하나의 값 방출
 * */

fun main() = runBlocking<Unit> {

    val ob = scanTest()

    println("----- scan() -----")

    ob.scan { prev, next -> "$prev$next" } // 이전 값과 다음값을 합해서 전달
        .subscribe { println(it) }

    println("")
    println("----- reduce() -----")

    ob.reduce { prev, next -> "$prev$next" } // 이전 연산을 모두 완료하고 마지막 결과만 전달 (scan의 마지막 값)
        .subscribe { println(it) }

    println("")
    println("----- scan() with debounce() -----")

    ob.scan { prev, next -> "$prev$next" } // debounce와 사용 가능
        .debounce(200, TimeUnit.MILLISECONDS)
        .subscribe { println(it) }

}

fun scanTest(): Observable<String> = Observable.create {
    runBlocking {
        it.onNext("고")
        it.onNext("구")
        it.onNext("마")
        it.onNext(" ")
        delay(300)
        it.onNext("맛")
        it.onNext("있")
        it.onNext("게")
        it.onNext(" ")
        delay(300)
        it.onNext("먹")
        it.onNext("는")
        it.onNext("법")
        it.onComplete()
    }
}
