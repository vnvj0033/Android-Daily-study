package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun main() {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val disposable = observable.subscribe(
        { item ->
            println("onNext - $item")
        },
        { err ->
            println("onError - $err")
        }, { println("onComplete()") }
    )

    // 0.5초 대기후 dispose() 호출
    Thread {
        Thread.sleep(500)
        disposable.dispose()
        Thread.sleep(100)
    }.apply {
        start()
        join()
    }
}
