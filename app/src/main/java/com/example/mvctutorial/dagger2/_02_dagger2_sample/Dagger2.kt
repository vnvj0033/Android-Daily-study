package com.example.mvctutorial.dagger2._02_dagger2_sample

import com.example.mvctutorial.dagger2._01_di_sample.IronMan
import com.example.mvctutorial.dagger2._01_di_sample.Person
import com.example.mvctutorial.dagger2._01_di_sample.Suit
import com.example.mvctutorial.dagger2._01_di_sample.Weapon
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 * module은 객체를 생성해서 공급
 * */
@Module
class HeroModule {
    @Provides
    fun providePerson(): Person = IronMan()

    @Provides
    fun provideWeapon(): Weapon = Suit()

    @Provides
    fun provideHero(person: Person, weapon: Weapon) = Hero(person, weapon)
}

/**
 * component는 객체를 생성하기 위해 제공되는 interface
 * */
@Component(modules = [HeroModule::class])
interface HeroComponent {
    fun callHero(): Hero
    fun callWeapon(): Weapon
    fun callPerson(): Person
}

/**
 * Component에서 Hero 객체 자체를 생성하는 객체가 필요
 * */
class Hero {
    val person: Person
    val weapon: Weapon

    @Inject constructor(person: Person,  weapon: Weapon) {
        this.person = person
        this.weapon = weapon
    }

    fun info() {
        println("name: ${person.name()} skill: ${person.skill()} | weapon:${weapon.type()}")
    }
}

fun main() {
    val hero = DaggerHeroComponent.create().callHero()
    hero.info()

    val person = DaggerHeroComponent.create().callPerson()
    val weapon = DaggerHeroComponent.create().callWeapon()

    println(person.name() + " " + weapon.type())
}
