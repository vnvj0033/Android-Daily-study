package com.example.mvctutorial.designpattern._11decorator.concrete;

import com.example.mvctutorial.designpattern._11decorator.abst.AbstAdding;
import com.example.mvctutorial.designpattern._11decorator.abst.IBeverage;

public class Milk extends AbstAdding {

    public Milk(IBeverage base) {
        super(base);
    }

    @Override
    public int getTotalPrice() {
        return super.getTotalPrice()+50;
    }
}
