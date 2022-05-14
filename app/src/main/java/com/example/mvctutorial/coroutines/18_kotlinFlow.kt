package com.example.mvctutorial.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    representingMultipleValues()
//    sequences()
    asynchronousFlow()
}

/**
 * 다수의 값은 코틀린 콜렌션을 통해 가능하다. 다음은 List를 forEach를 사용해 출력
 * */
private fun representingMultipleValues() = runBlocking {
    listOf(1, 2, 3).forEach { value -> println(value) }
}


/**
 * 자원이 소모되는 작업에는 시퀀스를 이용할 수 있다.
 * Sequence는 객체를 생성하는 시간을 yield를 만나 값을 전달하고 객체가 생성까지 지연시킨다.
 * */
private fun sequences() = runBlocking {

    fun foo(): Sequence<Int> = sequence { // sequence builder
        for (i in 1..3) {
            Thread.sleep(1000) // pretend we are computing it
            yield(i) // yield next value
        }
    }

    foo().forEach { println(it) }
}

/**
 * Flow는 flow {}, 빌더를 사용해 생성하며 빌더중 코드는 언제든 중단이 가능함
 * 값은 emit를 사용해 배출하며 collect를 통해 방출
 * */
private fun asynchronousFlow() = runBlocking {

    fun foo(): Flow<Int> = flow { // flow builder
        for (i in 1..3) {
            delay(100) // pretend we are doing something useful here
            emit(i) // emit next value
        }
    }

    println("main start!")
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay (100)
        }
    }

    foo().collect { println(it) }
    println ("main end!")


}