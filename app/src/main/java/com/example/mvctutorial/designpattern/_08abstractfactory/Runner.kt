package com.example.mvctutorial.designpattern._08abstractfactory

import com.example.mvctutorial.designpattern._08abstractfactory.gt.GtFactory
import com.example.mvctutorial.designpattern._08abstractfactory.samchuly.SamFactory

fun main() {

    // factory의 클래스만 바꾸면 create의 비지니스 로직을 변경함
//   val factory: BikeFactory = SamFactory()
    val factory: BikeFactory = GtFactory()

    val body = factory.createBody()
    val wheel = factory.createWheel()



    println("${body.javaClass}")
    println("${wheel.javaClass}")

}
