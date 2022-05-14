package com.example.mvctutorial.coroutines

import kotlinx.coroutines.runBlocking

fun main() {
    representingMultipleValues()
}

/**
 * 다수의 값은 코틀린 콜렌션을 통해 가능하다. 다음은 List를 forEach를 사용해 출력
 * */
private fun representingMultipleValues() = runBlocking {
    listOf(1, 2, 3).forEach { value -> println(value) }
}
