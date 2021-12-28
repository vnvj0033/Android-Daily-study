package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

fun main() {
    val list = listOf(1, 2, 3)
    val num = 3
    val str = "wow!"
    val map = mapOf(1 to "one", 2 to "two")

    // just는 받은 인자를 그대로 Observable로 만든다.
    val justOb = Observable.just(list, num, str, map)

    val observer= object : Observer<Any> {
        override fun onComplete() = println("onComplete()")
        override fun onNext(item: Any) = println("onNext() - $item")
        override fun onError(e: Throwable) = println("onError() - $")
        override fun onSubscribe(d: Disposable) = println("onSubscribe() - $d ")
    }

    justOb.subscribe(observer)

}
