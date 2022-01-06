package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.kotlin.toObservable

/** connectable observer은 connect를 호출하면 배출을 시작  */
fun main() {
    // publish()를 통하여 hot observable로 변경
    val connectableObservable = (1..10).toObservable().publish()

    // 1번 구독자 등록
    connectableObservable.subscribe { println("first subscriber: $it") }
    println ("Add first subscriber.")

    // 2번 구독자 등록
    connectableObservable.map { "second subscriber: $it" }
        .subscribe { println(it) }
    println ("Add second subscriber.")

    // 연결 시작
    connectableObservable.connect()

    // 3번 구독자 등록, observable이 종료된후 구독되어 실행 x
    connectableObservable.subscribe { println("Subscription 3: $it") }
}
