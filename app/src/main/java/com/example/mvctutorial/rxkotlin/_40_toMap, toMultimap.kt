package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking

/**
 *  toMap : key와 value값 저장
 *  toMultimap : 하나의 key로 grouping 저장
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.range(1, 10)

    ob.toMap({ key -> key }, { value -> value * value }) // 키에 제곱을 저장해 Map으로 전달
        .subscribeBy { println(it) }

    ob.toMultimap {
        if (it % 2 == 0) { // 짝수의 경우 even의 키를 가지는 Multimap 저장
            "even"
        } else { // 홀수의 경우 odd의 키를 가지는 Multimp 저장
            "odd"
        }
    }.subscribeBy { println(it) }

}
