package com.example.mvctutorial.designpattern._02adapter

object Math {
    //두배
    fun twoTime(num: Double): Double = num * 2

    //절반
    fun half(num: Double): Double = num / 2

    //강화된 알고리즘
    fun doubled(d: Double): Double = d * 2
}