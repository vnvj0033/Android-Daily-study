package com.example.mvctutorial.rxkotlin

fun main() {
    val list = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f) // 1
    val iterator = list.iterator() // 2
    while (iterator.hasNext()) { // 3
        println(iterator.next()) // 4 각 엘리먼트를 출력한다
    }
}
