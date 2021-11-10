package com.example.mvctutorial.designpattern._13chain_of_resposibility.part1

fun main() {
    val plus = PlusCalculator()
    val sub = SubCalculator()

    plus.setNextCalculator(sub)

    val request1 = Request(1, 2, "+")
    val request2 = Request(10, 8, "-")

    plus.process(request1)
    plus.process(request2)

}