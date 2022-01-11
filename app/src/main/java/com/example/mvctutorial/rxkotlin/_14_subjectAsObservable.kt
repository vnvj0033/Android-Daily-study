package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject

/** Observable없이 subject만으로 Observable역할 수행 */
fun main() {
    val observer = object : Observer<String> {
        override fun onComplete() {}
        override fun onNext(item: String) = println("onNext() - $item")
        override fun onError(e: Throwable) {}
        override fun onSubscribe(d: Disposable) {}
    }
    val publicSubject = PublishSubject.create<String>()
    publicSubject.subscribe(observer)

    val asyncSubject = AsyncSubject.create<String>()
    asyncSubject.subscribe(observer)

    val behaviorSubject = BehaviorSubject.create<String>()
    behaviorSubject.subscribe(observer)

    val replaySubject = ReplaySubject.create<String>()
    replaySubject.subscribe(observer)

    // onNext를 바로 호출
    publicSubject.onNext("public")
    asyncSubject.onNext("async")
    behaviorSubject.onNext("behavior")
    replaySubject.onNext("replay")


    asyncSubject.onComplete()
}
