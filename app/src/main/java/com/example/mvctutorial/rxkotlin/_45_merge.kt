package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking

/**
 * merge: Observable의 static 함수로 최대 4개까지 병합
 * mergeArray: Observable의 static 함수로 args로 인자를 받아 다수의 Observable 병합 가능
 * mergeWith: Observable의 instance 객체를 이용하여 다른 Observable과 병합
 * */

fun main() = runBlocking<Unit> {

    val ob1 = Observable.just(1, 2)
    val ob2 = Observable.just(3, 4)

    println("----- merge() ------")

    Observable.merge(ob1, ob2)
        .subscribe { println(it) }

    println("----- mergeWith() ------")

    ob1.mergeWith(ob2)
        .subscribe { println(it) }

    println("----- mergeArray() ------")

    val ob3 = Observable.just(5, 6)
    val ob4 = Observable.just(7, 8)
    val ob5 = Observable.just(9, 10)

    Observable.mergeArray(ob1, ob2, ob3, ob4, ob5)
        .subscribe { println(it) }

}
