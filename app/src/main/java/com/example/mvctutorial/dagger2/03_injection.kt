package com.example.mvctutorial.dagger2

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class HeroInjectModule {
    @Provides
    fun providePerson(): Person = IronMan()
    @Provides
    fun provideWeapon(): Weapon = Suit()
}


@Component(modules = [HeroInjectModule::class])
interface HeroInjectComponent {
    fun inject(hero: HeroInject)
}

class HeroInject {

    @Inject
    lateinit var person: Person
    @Inject
    lateinit var weapon: Weapon

    fun info() {
        println("name: ${person.name()} skill: ${person.skill()} | weapon:${weapon.type()}")
    }
}

fun main() {
    val hero = HeroInject()
    DaggerHeroInjectComponent.create().inject(hero)

    hero.info()
}
