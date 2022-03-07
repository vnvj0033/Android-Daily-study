package com.example.mvctutorial.dagger2._04_qualifiers

import javax.inject.Inject
import javax.inject.Named

interface Person {
    fun name(): String
    fun skill(): String
}

interface Weapon {
    fun type(): String
}

class Hero @Inject constructor(@Named("ironMan") private val person: Person, @Named("suit") private val weapon: Weapon) {
    fun info() {
        println("name: ${person.name()} skill: ${person.skill()} | weapon:${weapon.type()}")
    }
}

class IronMan : Person {
    override fun name() = "토니 스타크"
    override fun skill() = "수트 변형"
}

class Suit : Weapon {
    override fun type() = "수트"
}
