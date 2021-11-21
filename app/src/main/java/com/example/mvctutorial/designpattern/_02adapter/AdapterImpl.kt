package com.example.mvctutorial.designpattern._02adapter

class AdapterImpl: Adapter {
    override fun twiceOf(num: Float): Double = Math.doubled(num.toDouble())
    override fun halfOf(num: Float): Double = Math.half(num.toDouble())
}