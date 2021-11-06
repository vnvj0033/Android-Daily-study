package com.example.mvctutorial.designpattern._11decorator.concrete;

import com.example.mvctutorial.designpattern._11decorator.abst.IBeverage;

public class Base implements IBeverage {
    @Override
    public int getTotalPrice() {
        return 0;
    }
}
