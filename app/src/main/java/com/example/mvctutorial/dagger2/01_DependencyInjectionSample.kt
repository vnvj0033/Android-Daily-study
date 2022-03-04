package com.example.mvctutorial.dagger2

interface Person {
    fun name(): String
    fun skill(): String
}

interface Weapon {
    fun type(): String
}

class Hero(private val person: Person, private val weapon: Weapon) {
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

fun main() {
    val person = IronMan()
    val weapon = Suit()
    val hero = Hero(person, weapon)

    hero.info()
}
