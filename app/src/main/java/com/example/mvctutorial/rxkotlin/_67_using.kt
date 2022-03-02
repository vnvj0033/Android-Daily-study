package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import kotlinx.coroutines.runBlocking

/**
 * using의 3개 param ramda
 * 1. resource의 선언
 * 2. resource의 사용 및 Obserbable을 return
 * 3. reousrce의 해제
 *
 * 도중 에러가 있어도 Observer로 전달
 * */

fun main() = runBlocking<Unit> {
    Observable.using({
        DataLoad()
    }, { data ->
        data.list.toObservable()
    }, { data ->
        data.release()
    }).map {
        if (it == 5) { throw Exception("Wow!! exception") }
        else { it }
    }.subscribeBy(
        onNext = { println(it) },
        onComplete = { println("completed!!") },
        onError = { println(it) }
    )

}

class DataLoad {
    val list: MutableList<Int> = mutableListOf()

    init {
        (1..10).forEach { list.add(it) }
    }

    fun release() {
        list.clear()
        println("release source")
    }
}
