package com.example.mvctutorial.designpattern._20proxy

class Proxy : Subject {

    private val real = RealSubject()

    override fun action1() {
        println("간단한 업무 by proxy")
    }

    override fun action2() {
        real.action2()
    }
}