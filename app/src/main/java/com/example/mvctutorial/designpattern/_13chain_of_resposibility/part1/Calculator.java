package com.example.mvctutorial.designpattern._13chain_of_resposibility.part1;

public abstract class Calculator {
    Calculator nextCalculator;

    public void setNextCalculator(Calculator nextCalculator) {
        this.nextCalculator = nextCalculator;
    }

    boolean process(Request request) {

        if (nextCalculator == null) return false;

        if (operator(request)){
            return true;
        }else{
            return nextCalculator.operator(request);
        }

    }

    abstract protected boolean operator(Request request);
}
