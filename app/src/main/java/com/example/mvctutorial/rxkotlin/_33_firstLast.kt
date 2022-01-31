package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking


/**
 *  first는 가장 처음 전달자를
 *  last는 마지막 전달자를 반환
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.range(1, 100)

    ob.first(2) //first 값이 없으면 기본값 2 출력
        .subscribeBy { println("first item: $it") }

    ob.last(2) //last 값이 없으면 기본값 2 출력
        .subscribeBy { println("last item: $it") }

    Observable.empty<Long>().first(-1) // 빈 Observable이므로 first가 없음 -> 기본값인 -1 출력
        .subscribeBy { println("default item: $it") }

}
