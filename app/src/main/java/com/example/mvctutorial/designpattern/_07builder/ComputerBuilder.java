package com.example.mvctutorial.designpattern._07builder;

public class ComputerBuilder {
    private Computer computer;

    private ComputerBuilder(){
        computer = new Computer("","","");
    }

    public static ComputerBuilder start() {
        return new ComputerBuilder();
    }

    public ComputerBuilder setCpu(String cpu) {
        computer.cpu = cpu;
        return this;
    }

    public ComputerBuilder setRam(String ram) {
        computer.ram = ram;
        return this;
    }

    public ComputerBuilder setStorage(String storage) {
        computer.storage = storage;
        return this;
    }

    public Computer build() {
        return computer;
    }
}
