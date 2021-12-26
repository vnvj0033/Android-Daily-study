package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

fun main() {

    // Observable 기본 생성은 Observable.create<T>
    // 그 후에 Observer 객체를 subscribe 한다.

    val observable1 = Observable.create<Int> {
        it.onNext(1)
        it.onNext(2)
        it.onComplete()
    }

    val observable2 = Observable.create<Int> {
        it.onNext(1)
        it.onNext(2)
        it.onError(Exception("Wow!! exception"))
    }

    val observer = object : Observer<Int> {
        override fun onComplete() = println("onComplete()")
        override fun onNext(item: Int) = println("onNext() - $item")
        override fun onError(e: Throwable) = println("onError() - $")
        override fun onSubscribe(d: Disposable) = println("onSubscribe() - $d ")
    }

    observable1.subscribe(observer)
    observable2.subscribe(observer)
}

