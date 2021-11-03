package com.example.mvctutorial.designpattern._07builder;

abstract class BluePrint {
    abstract void setCpu();

    abstract void setRam();

    abstract void setStorage();

    abstract Computer getComputer();
}
