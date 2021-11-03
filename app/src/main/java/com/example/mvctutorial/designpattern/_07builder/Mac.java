package com.example.mvctutorial.designpattern._07builder;

public class Mac extends BluePrint{

    private Computer computer;

    public Mac() {
        this.computer = new Computer("","","");
    }

    @Override
    void setCpu() {
        computer.cpu = "m1 pro max";
    }

    @Override
    void setRam() {
        computer.ram = "32g";
    }

    @Override
    void setStorage() {
        computer.storage = "1T ssd";
    }

    @Override
    Computer getComputer() {
        return computer;
    }

}
