package com.example.mvctutorial.designpattern._11decorator.abst

open class AbstAdding(private val base: IBeverage): IBeverage {

    override fun getTotalPrice() = base.getTotalPrice()
}