package com.example.mvctutorial.designpattern._01strategy_weapon

fun main() {
    val character = GameCharacter()

    character.run {
        setWeapon(Knife())
        attack()
    }

    character.run {
        setWeapon(Ax())
        attack()
    }

    character.run {
        setWeapon(Sword())
        attack()
    }
}
