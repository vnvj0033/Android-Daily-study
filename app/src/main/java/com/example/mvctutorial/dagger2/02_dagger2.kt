package com.example.mvctutorial.dagger2

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
    fun provideHero(person: Person, weapon: Weapon) = HeroDagger(person, weapon)
}

/**
 * component는 객체를 생성하기 위해 제공되는 interface
 * */
@Component(modules = [HeroModule::class])
interface HeroComponent {
    fun callHero(): HeroDagger
}

/**
 * Component에서 Hero 객체 자체를 생성하는 객체가 필요
 * */
class HeroDagger @Inject constructor(val person: Person, val weapon: Weapon) {
    fun info() {
        println("name: ${person.name()} skill: ${person.skill()} | weapon:${weapon.type()}")
    }
}

fun main() {
    val hero = DaggerHeroComponent.create().callHero()
    hero.info()
}
