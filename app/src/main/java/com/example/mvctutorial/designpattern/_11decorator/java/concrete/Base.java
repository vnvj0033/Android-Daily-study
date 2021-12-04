package com.example.mvctutorial.designpattern._11decorator.java.concrete;

import com.example.mvctutorial.designpattern._11decorator.java.abst.IBeverage;

public class Base implements IBeverage {
    @Override
    public int getTotalPrice() {
        return 0;
    }
}
