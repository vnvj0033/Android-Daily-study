package com.example.mvctutorial.designpattern._12visitor

class VisitorA(var number: Int = 0): Visitor {
    override fun visit(visitable: Visitable) {

        if (visitable is VisitableA){
            number += visitable.numberOfMember

        }
    }
}