package com.example.mvctutorial.designpattern._18memento

class Originator {
    var state = ""

    fun createMemento() = Memento(state)

    fun restoreMemento(memento: Memento) {
        this.state = memento.state
    }
}