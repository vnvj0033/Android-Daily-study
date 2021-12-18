package com.example.mvctutorial.designpattern._19flyweight

import java.util.*

class FlyweightFactory {

    private val pool = TreeMap<String, Flyweight>()

    fun getFlyweight(key: String): Flyweight {
        var flyweight = pool[key]

        if (flyweight == null) {
            flyweight = Flyweight(key)
            pool[key] = flyweight
            println("새로 사용 $key")
        }else {
            println("재사용 $key")
        }
        return flyweight
    }
}