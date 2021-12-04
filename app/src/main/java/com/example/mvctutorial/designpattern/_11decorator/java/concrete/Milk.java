package com.example.mvctutorial.designpattern._11decorator.java.concrete;

import com.example.mvctutorial.designpattern._11decorator.java.abst.AbstAdding;
import com.example.mvctutorial.designpattern._11decorator.java.abst.IBeverage;

public class Milk extends AbstAdding {

    public Milk(IBeverage base) {
        super(base);
    }

    @Override
    public int getTotalPrice() {
        return super.getTotalPrice()+50;
    }
}
