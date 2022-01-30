package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking


/**
 *  elementAt는 배열 연산자와 동일하게 특정 순서에 배출되는 데이터를 획득할수 있습니다.
 *  ignoreElements 배출이 완료 되었는지에 대한 판단
 * */

fun main() = runBlocking<Unit> {

    val ob = Observable.range(1, 50)

    ob.elementAt(3).subscribe { println("element 3rd: $it") } // 3번째 원소 (4)

    ob.elementAt(100).subscribe { println("element 100th: $it") } // 갯수가 51번째여서 100번째는 실행되지 않음

    ob.ignoreElements().subscribe { println("emission completed") } // 완료시 실행

} 
