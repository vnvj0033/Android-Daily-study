package com.example.mvctutorial.designpattern._11decorator.abst

class AbstAdding(private val base: IBeverage): IBeverage {

    override fun getTotalPrice(): Int = base.getTotalPrice()
}