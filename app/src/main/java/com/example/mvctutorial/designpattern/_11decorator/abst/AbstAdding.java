package com.example.mvctutorial.designpattern._11decorator.abst;

public class AbstAdding implements IBeverage{

    private IBeverage base;

    public AbstAdding(IBeverage base) {
        this.base = base;
    }

    @Override
    public int getTotalPrice() {
        return base.getTotalPrice();
    }
}
