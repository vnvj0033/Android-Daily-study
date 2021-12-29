package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

fun main() {

    val observer = object : Observer<Int> {
        override fun onComplete() = println("onComplete()")
        override fun onNext(item: Int) = println("onNext() - $item")
        override fun onError(e: Throwable) = println("onError() - $")
        override fun onSubscribe(d: Disposable) = println("onSubscribe() - $d ")
    }

    // range는 일정 범위만큼 수를 Observable로 만들어줌
    Observable.range(1, 3).subscribe(observer)

}
