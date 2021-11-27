package com.example.mvctutorial.designpattern._08abstractfactory.gt

import com.example.mvctutorial.designpattern._08abstractfactory.BikeFactory
import com.example.mvctutorial.designpattern._08abstractfactory.Body
import com.example.mvctutorial.designpattern._08abstractfactory.Wheel

class GtFactory: BikeFactory {
    override fun createBody(): Body = GtBody()

    override fun createWheel(): Wheel = GtWheel()
}