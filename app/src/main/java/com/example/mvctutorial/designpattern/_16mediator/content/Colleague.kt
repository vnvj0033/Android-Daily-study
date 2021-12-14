package com.example.mvctutorial.designpattern._16mediator.content

abstract class Colleague {

    private lateinit var mediator: Mediator

    open fun join(mediator: Mediator): Boolean {
        this.mediator = mediator
        return mediator.addColleague(this)
    }

    open fun sendData(data: String) {
        mediator.mediate(data)
    }

    abstract fun handle(data: String)
}