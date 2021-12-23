package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

fun main() {
    observerBase()

}


fun observerBase() {

    val observable1 = Observable.create<String> {
        it.onNext("1")
        it.onNext("2")
        it.onError(Exception("throw error"))
        it.onComplete()
    }

    val observer = object : Observer<String> {
        override fun onComplete() = println("onComplete()")
        override fun onNext(item: String) = println("onNext() - $item")
        override fun onError(e: Throwable) = println("onError() - $e")
        override fun onSubscribe(d: Disposable) = println("onSubscribe() - $d ")
    }

    observable1.subscribe(observer)
}
