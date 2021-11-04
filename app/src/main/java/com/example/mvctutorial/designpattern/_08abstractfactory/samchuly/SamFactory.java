package com.example.mvctutorial.designpattern._08abstractfactory.samchuly;

import com.example.mvctutorial.designpattern._08abstractfactory.BikeFactory;
import com.example.mvctutorial.designpattern._08abstractfactory.Body;
import com.example.mvctutorial.designpattern._08abstractfactory.Wheel;

public class SamFactory implements BikeFactory {
    @Override
    public Body createBody() {
        return new SamBody();
    }

    @Override
    public Wheel createWheel() {
        return new SamWheel();
    }
}
