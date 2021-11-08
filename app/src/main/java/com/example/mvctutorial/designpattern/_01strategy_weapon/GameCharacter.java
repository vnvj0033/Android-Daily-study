package com.example.mvctutorial.designpattern._01strategy_weapon;


public class GameCharacter {

    // 체력,마력,스테미너 등..

    private Weapon weapon;

    public int attack() {

        return weapon.doAttack();
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

}