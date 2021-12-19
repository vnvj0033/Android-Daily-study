package com.example.mvctutorial.designpattern._20proxy

class RealSubject: Subject {
    override fun action1() {
        println("간단한 업무 by real")
    }

    override fun action2() {
        println("복잡한 업무 by real")
    }
}