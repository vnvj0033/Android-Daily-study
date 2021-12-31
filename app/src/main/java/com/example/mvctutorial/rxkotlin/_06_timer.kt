package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

fun main() {
    val observer = object : Observer<Long> {
        override fun onComplete() = println("onComplete()")
        override fun onNext(item: Long) = println("onNext() - $item")
        override fun onError(e: Throwable) = println("onError() - $")
        override fun onSubscribe(d: Disposable) = println("onSubscribe() - $d ")
    }

    // timer은 정해진 시간에 한번 호출
    Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(observer)

    Thread() .start()

    // 0.3초간 main thread를 대기시킨다.
    val th1 = Thread { Thread.sleep(3000)}
    th1.start()
    th1.join()

}
