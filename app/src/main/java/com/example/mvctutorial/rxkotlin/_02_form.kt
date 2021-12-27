package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

fun main() {
    val list = listOf(1, 2, 3)

    // fromIterable 는 리스트의 아이템을 Observable객체로 만들어준다.
    val listOb = Observable.fromIterable(list)

    // Callable은 Runnable과 비슷하지만 리턴 값이 있음
    val call = Callable { 4 }
    // fromCallable는 Callable의 return값을 Observable로 만들어준다.
    val callOb = Observable.fromCallable(call)

    // Future는 비동기 작업의 결과를 가저오는 객체 여튼 그렇다고함...
    val future = object : Future<Int> {
        override fun get() = 5
        override fun get(timeout: Long, unit: TimeUnit) = 6
        override fun isDone() = true
        override fun cancel(mayInterruptIfRunning: Boolean) = false
        override fun isCancelled() = false
    }

    // fromFuture은 Future의 get()값을 Observable로 만들어줌
    val futureOb = Observable.fromFuture(future)

    val observer: Observer<Int> = object : Observer<Int> {
        override fun onComplete() = println("onComplete()")
        override fun onNext(item: Int) = println("onNext() - $item")
        override fun onError(e: Throwable) = println("onError() - $")
        override fun onSubscribe(d: Disposable) = println("onSubscribe() - $d ")
    }

    listOb.subscribe(observer)
    callOb.subscribe(observer)
    futureOb.subscribe(observer)
}
