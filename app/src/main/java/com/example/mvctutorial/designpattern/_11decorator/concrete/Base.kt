package com.example.mvctutorial.designpattern._11decorator.concrete

import com.example.mvctutorial.designpattern._11decorator.abst.IBeverage

class Base: IBeverage {
    override fun getTotalPrice() = 0
}