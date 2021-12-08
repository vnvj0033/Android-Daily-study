package com.example.mvctutorial.designpattern._12visitor.java;

public class VisitableA implements Visitable{
    int numberOfMember;

    public VisitableA(int numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
