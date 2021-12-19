package com.example.mvctutorial.designpattern._20proxy

fun main() {
    val real = RealSubject()
    val proxy1 = Proxy()
    val proxy2 = Proxy()

    proxy1.action1()
    proxy2.action1()

    proxy1.action2()
    proxy2.action2()

    real.action1()
    real.action2()
}
