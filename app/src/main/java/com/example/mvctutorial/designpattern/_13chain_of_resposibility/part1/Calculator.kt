package com.example.mvctutorial.designpattern._13chain_of_resposibility.part1

abstract class Calculator {

    var nextCalculator: Calculator? = null

    fun process(request: Request): Boolean {

        nextCalculator ?: return false

        return if (operator(request)) true
        else nextCalculator!!.operator(request)
    }

    protected abstract fun operator(request: Request): Boolean
}