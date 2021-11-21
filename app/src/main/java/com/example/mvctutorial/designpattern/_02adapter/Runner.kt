package com.example.mvctutorial.designpattern._02adapter

fun main() {
    val adapter = AdapterImpl()

    println(adapter.halfOf(100.3F))
    println(adapter.twiceOf(100.3F))
}