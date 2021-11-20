package com.example.mvctutorial.designpattern._01strategy_weapon

class GameCharacter {

    lateinit var weapon: Weapon

    fun attack() {
        weapon.doAttack()
    }
}