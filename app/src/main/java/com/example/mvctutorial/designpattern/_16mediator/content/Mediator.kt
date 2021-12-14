package com.example.mvctutorial.designpattern._16mediator.content

import java.util.ArrayList

abstract class Mediator {
    var colleagues: MutableList<Colleague> = ArrayList()

    open fun addColleague(colleague: Colleague): Boolean {
        return colleagues.add(colleague)
    }

    abstract fun mediate(data: String)
}