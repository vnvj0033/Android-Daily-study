package com.example.mvctutorial.designpattern._09bidge;

public class MorseCode {

    private MorseCodeFunction function;

    public MorseCode(MorseCodeFunction function) {
        this.function = function;
    }

    public MorseCodeFunction getFunction() {
        return function;
    }

    public void setFunction(MorseCodeFunction function) {
        this.function = function;
    }

    void dot(){
        function.dot();
    }

    void dash(){
        function.dash();
    }

    void space(){
        function.space();
    }
}
