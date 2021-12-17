package com.example.mvctutorial.designpattern._18memento

import java.util.*

fun main() {

    val mementos: Stack<Memento> = Stack()

    val originator = Originator()

    originator.state = "state 1"
    mementos.push(originator.createMemento())

    originator.state = "state 2"
    mementos.push(originator.createMemento())

    originator.state = "state 3"
    mementos.push(originator.createMemento())

    originator.state = "state Final"
    mementos.push(originator.createMemento())

    originator.restoreMemento(mementos.pop())
    println(originator.state) //state Final

    originator.restoreMemento(mementos.pop())
    println(originator.state) //state 3

    originator.restoreMemento(mementos.pop())
    println(originator.state) //state 2

    originator.restoreMemento(mementos.pop())
    println(originator.state) //state 1
}