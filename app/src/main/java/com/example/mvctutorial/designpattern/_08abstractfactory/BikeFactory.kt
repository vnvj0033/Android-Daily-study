package com.example.mvctutorial.designpattern._08abstractfactory

interface BikeFactory {

    fun createBody(): Body
    fun createWheel(): Wheel
}