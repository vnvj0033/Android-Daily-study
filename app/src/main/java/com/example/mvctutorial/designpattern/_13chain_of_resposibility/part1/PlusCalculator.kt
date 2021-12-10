package com.example.mvctutorial.designpattern._13chain_of_resposibility.part1

class PlusCalculator: Calculator() {
    override fun operator(request: Request): Boolean {
        if (request.operator == "+") {
            val a = request.a
            val b = request.b
            val result = a + b
            println("$a + $b = $result ")
        }
        return false
    }
}