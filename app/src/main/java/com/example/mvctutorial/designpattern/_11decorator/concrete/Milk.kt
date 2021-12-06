package com.example.mvctutorial.designpattern._11decorator.concrete

import com.example.mvctutorial.designpattern._11decorator.abst.AbstAdding
import com.example.mvctutorial.designpattern._11decorator.abst.IBeverage

class Milk(val base: IBeverage): AbstAdding(base) {

    override fun getTotalPrice() = super.getTotalPrice() + 50

}