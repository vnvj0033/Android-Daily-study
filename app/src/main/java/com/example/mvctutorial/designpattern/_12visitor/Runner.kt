package com.example.mvctutorial.designpattern._12visitor

import com.example.mvctutorial.designpattern._12visitor.java.Visitable
import com.example.mvctutorial.designpattern._12visitor.java.VisitableA
import com.example.mvctutorial.designpattern._12visitor.java.VisitorA

fun main() {
    val visitables = ArrayList<Visitable>().apply {
        add(VisitableA(1))
        add(VisitableA(2))
        add(VisitableA(3))
        add(VisitableA(4))
        add(VisitableA(5))
    }

    val visitor =
        VisitorA()

    for (va in visitables) {
        va.accept(visitor)
    }

    println(visitor.number)
}
