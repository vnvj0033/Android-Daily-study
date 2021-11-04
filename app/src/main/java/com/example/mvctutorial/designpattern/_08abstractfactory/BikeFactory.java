package com.example.mvctutorial.designpattern._08abstractfactory;

public interface BikeFactory {

    public Body createBody();
    public Wheel createWheel();
}
