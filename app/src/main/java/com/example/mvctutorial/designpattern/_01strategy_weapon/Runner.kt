package com.example.mvctutorial.designpattern._01strategy_weapon

fun main() {
    val character = GameCharacter()

    character.run {
        weapon = Ax()
        attack()
    }

    character.run {
        weapon = Knife()
        attack()
    }

    character.run {
        weapon = Sword()
        attack()
    }
}