package com.example.mvctutorial.di.dagger2_pre._03_injection

import com.example.mvctutorial.di.dagger2_pre._01_di_sample.IronMan
import com.example.mvctutorial.di.dagger2_pre._01_di_sample.Person
import com.example.mvctutorial.di.dagger2_pre._01_di_sample.Suit
import com.example.mvctutorial.di.dagger2_pre._01_di_sample.Weapon
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class HeroModule {
    @Provides
    fun providePerson(): Person = IronMan()
    @Provides
    fun provideWeapon(): Weapon = Suit()
    @Provides
    fun provideHero(person: Person, weapon: Weapon) = Hero(person, weapon)
}


@Component(modules = [HeroModule::class])
interface HeroComponent {
    fun inject(hero: Hero)
    fun callHero(): Hero
}

class Hero {
    @Inject
    lateinit var person: Person
    @Inject
    lateinit var weapon: Weapon

    constructor()
    constructor(person: Person, weapon: Weapon) {
        this.person = person
        this.weapon = weapon
    }

    fun info() {
        println("name: ${person.name()} skill: ${person.skill()} | weapon:${weapon.type()}")
    }
}

fun main() {
    val hero = Hero()
    DaggerHeroComponent.create().inject(hero)

    hero.info()

    val h2 = DaggerHeroComponent.create().callHero()
    h2.info()
}
