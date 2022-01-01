package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

fun main() {
    lateinit var disposable: Disposable
    val observer = object : Observer<Long> {
        override fun onComplete() = println("onComplete()")
        override fun onNext(item: Long) = println("onNext() - $item")
        override fun onError(e: Throwable) = println("onError() - $")
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe() - $d ")
            disposable = d
        }
    }

    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)

    Thread {
        Thread.sleep(500)
        println("dispose")
        disposable.dispose()
    }.start()

    observable.subscribe(observer) // 0.5초 대기후 dispose() 호출
    Thread { Thread.sleep(1000) }.apply {
        start()
        join()
    }
}
