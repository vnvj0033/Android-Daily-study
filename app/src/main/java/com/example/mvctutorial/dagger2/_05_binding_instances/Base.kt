package com.example.mvctutorial.dagger2._05_binding_instances

import javax.inject.Inject
import javax.inject.Named


class Hulk : Person {
    override fun name() = "브루스 배너"
    override fun skill() = "육탄전"
}

class HulkBuster : Weapon {
    override fun type() = "헐크버스터"
}

class Hero @Inject constructor(@Named("ironMan") private val person: Person, @Named("suit") private val weapon: Weapon) {
    fun info() {
        println("name: ${person.name()} skill: ${person.skill()} | weapon:${weapon.type()}")
    }
}

interface Person {
    fun name(): String
    fun skill(): String
}

interface Weapon {
    fun type(): String
}
