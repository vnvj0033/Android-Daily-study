package com.example.mvctutorial.designpattern._12visitor

class VisitableA(val numberOfMember: Int): Visitable {
    override fun accept(vistor: Visitor) = vistor.visit(this)
}