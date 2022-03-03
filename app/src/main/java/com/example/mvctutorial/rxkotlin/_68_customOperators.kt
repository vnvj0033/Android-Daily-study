package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.runBlocking

/**
 * Observable이나 Flowable에 Extension function을 추가하지 않고도 커스텀 연산자를 선언
 * Observer로 선언된 부분을 custom하게 변경 하기 위하여 lift 함수를 제공하며, 인자로 ObservableOperator를 전달
 * */

fun main() = runBlocking<Unit> {

    Observable.range(1, 10)
        .lift(SquareNumber())
        .map { "($it)" }
        .subscribe { println("result: $it") }

    Observable.range(1, 10)
        .compose(SquareNumber2())
        .map { "square: $it" }
        .blockingSubscribe { println(it) }
}

class SquareNumber : ObservableOperator<String, Int> {

    override fun apply(observer: Observer<in String>): Observer<in Int> {
        return object : Observer<Int> {
            override fun onComplete() = observer.onComplete()
            override fun onSubscribe(d: Disposable) = observer.onSubscribe(d)
            override fun onError(e: Throwable) = observer.onError(e)
            override fun onNext(t: Int) {
                observer.onNext("Input value:$t square:${t * t}")
            }
        }
    }
}

class SquareNumber2 : ObservableTransformer<Int, Int> {
    override fun apply(upstream: Observable<Int>): ObservableSource<Int> {
        return upstream.map { it * it }
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.io())
    }
}
