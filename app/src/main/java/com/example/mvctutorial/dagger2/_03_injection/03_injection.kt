package com.example.mvctutorial.dagger2._03_injection

import com.example.mvctutorial.dagger2._01_di_sample.IronMan
import com.example.mvctutorial.dagger2._01_di_sample.Person
import com.example.mvctutorial.dagger2._01_di_sample.Suit
import com.example.mvctutorial.dagger2._01_di_sample.Weapon
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
}


@Component(modules = [HeroModule::class])
interface HeroComponent {
    fun inject(hero: Hero)
}

class Hero {

    @Inject
    lateinit var person: Person
    @Inject
    lateinit var weapon: Weapon

    fun info() {
        println("name: ${person.name()} skill: ${person.skill()} | weapon:${weapon.type()}")
    }
}

fun main() {
    val hero = Hero()
    DaggerHeroComponent.create().inject(hero)

    hero.info()
}
