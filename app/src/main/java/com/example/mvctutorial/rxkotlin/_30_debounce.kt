package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/**
 *  빠르게 데이터가 생산되는 경우 특정 시간이상 데이터 생산 간격이 벌어지는 경우에만 값을 출력
 * */

fun main(args: Array<String>) = runBlocking<Unit> {
    val ob = Observable.create<String> {
        runBlocking {
            it.onNext("고")
            it.onNext("고구")
            it.onNext("고구마")
            delay(100) // 100ms는 간격이 200ms 짧음으로 전달하지 않음
            it.onNext("고구마 맛")
            it.onNext("고구마 맛있")
            it.onNext("고구마 맛있게")
            delay(201) // 200ms 보다 간격이 넓음으로 데이터 전달함
            it.onNext("고구마 맛있게 먹")
            it.onNext("고구마 맛있게 먹는")
            it.onNext("고구마 맛있게 먹는법")
            it.onComplete()
        }
    }

    // 수신 데이터가 200ms 이상의 간격이 있으면 수신
    ob.debounce(200, TimeUnit.MILLISECONDS).subscribe {
        println(it)
    }
}
