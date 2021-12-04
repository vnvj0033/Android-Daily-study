package com.example.mvctutorial.designpattern._11decorator

import com.example.mvctutorial.designpattern._11decorator.java.concrete.Milk

import com.example.mvctutorial.designpattern._11decorator.java.concrete.Base

import com.example.mvctutorial.designpattern._11decorator.java.abst.IBeverage
import com.example.mvctutorial.designpattern._11decorator.java.concrete.Espresso
import java.util.*

fun main() {

    val sc = Scanner(System.`in`)
    var beverage: IBeverage = Base()

    var done = false

    while (!done) {
        println("음료 현재 가격 : " + beverage.totalPrice)
        print("선택 : 1:샷 추가 / 2:우유 추가")
        when (sc.nextInt()) {
            0 -> done = true
            1 -> beverage = Espresso(beverage)
            2 -> beverage = Milk(beverage)
        }
    }

    println("음료 가격 : " + beverage.totalPrice)
    sc.close()
}
