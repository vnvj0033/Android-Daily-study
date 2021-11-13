package com.example.mvctutorial.designpattern._14facade_pattern.system;
public class Facade {
    private HelpSystem01 system01;
    private HelpSystem02 system02;
    private HelpSystem03 system03;

    public Facade() {
        system01 = new HelpSystem01();
        system02 = new HelpSystem02();
        system03 = new HelpSystem03();
    }

    public void process() {
        system01.process();
        system02.process();
        system03.process();
    }
}
