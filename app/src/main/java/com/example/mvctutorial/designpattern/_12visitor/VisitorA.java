package com.example.mvctutorial.designpattern._12visitor;

public class VisitorA implements Visitor {
    private int number;

    public VisitorA() {
        this.number = 0;
    }

    @Override
    public void visit(Visitable visitable) {
        if (visitable instanceof VisitableA) {
            number += ((VisitableA) visitable).numberOfMember;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
