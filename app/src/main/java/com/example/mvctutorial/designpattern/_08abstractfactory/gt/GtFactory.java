package com.example.mvctutorial.designpattern._08abstractfactory.gt;

import com.example.mvctutorial.designpattern._08abstractfactory.BikeFactory;
import com.example.mvctutorial.designpattern._08abstractfactory.Body;
import com.example.mvctutorial.designpattern._08abstractfactory.Wheel;
import com.example.mvctutorial.designpattern._08abstractfactory.samchuly.SamBody;
import com.example.mvctutorial.designpattern._08abstractfactory.samchuly.SamWheel;

public class GtFactory implements BikeFactory {
    @Override
    public Body createBody() {
        return new GtBody();
    }

    @Override
    public Wheel createWheel() {
        return new GtWheel();
    }
}
