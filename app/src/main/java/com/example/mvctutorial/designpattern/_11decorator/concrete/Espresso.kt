package com.example.mvctutorial.designpattern._11decorator.concrete

import com.example.mvctutorial.designpattern._11decorator.abst.AbstAdding
import com.example.mvctutorial.designpattern._11decorator.abst.IBeverage

class Espresso(val base: IBeverage) : AbstAdding(base){

    companion object {
        private var espressoCount = 0

        /** 에스프레소 추가가격  */
        private fun getAddPrice(): Int {
            espressoCount += 1
            var addPrice = 100
            if (espressoCount > 1) {
                addPrice = 70
            }
            return addPrice
        }
    }

    override fun getTotalPrice() = super.getTotalPrice() + getAddPrice()
}