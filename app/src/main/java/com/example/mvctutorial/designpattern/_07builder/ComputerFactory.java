package com.example.mvctutorial.designpattern._07builder;

public class ComputerFactory {

    BluePrint print;

    public void setBuleprint(BluePrint print) {
        this.print = print;
    }

    public Computer makeComputer() {
        print.setCpu();
        print.setRam();
        print.setStorage();
        return print.getComputer();
    }
}
