package com.example.mvctutorial.di.dagger2_pre._04_qualifiers

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class HeroModule {
    @Provides
    @Named("ironMan")
    fun provideIronMan(): Person = IronMan()

    @Provides
    @Named("suit")
    fun provideSuit(): Weapon = Suit()

    @Provides
    @Named("captainAmerica")
    fun provideCaptainAmerica(): Person = CaptainAmerica()

    @Provides
    @Named("shield")
    fun provideShield(): Weapon = Shield()

    @Provides
    @Named("heroIronMan")
    fun provideHeroIronMan(@Named("ironMan") person: Person, @Named("suit") weapon: Weapon) =
        Hero(person, weapon)

    @Provides
    @Named("heroCaptainAmerica")
    fun provideHeroCaptainAmerica(
        @Named("captainAmerica") person: Person,
        @Named("shield") weapon: Weapon
    ) = Hero(person, weapon)

}

@Component(modules = [HeroModule::class])
interface HeroComponent {
    @Named("heroIronMan")
    fun callIronMan(): Hero

    @Named("heroCaptainAmerica")
    fun callCaptainAmerica(): Hero
}

// Hero 객체 생성
private fun main() {
    val heroIronMan = DaggerHeroComponent.create().callIronMan()
    val heroCaptain = DaggerHeroComponent.create().callCaptainAmerica()
}


class CaptainAmerica : Person {
    @Named("heroIronMan")
    override fun name() = "스티브 로저스"

    @Named("heroCaptainAmerica")
    override fun skill() = "방패 던지기"
}

class Shield : Weapon {
    override fun type() = "방패"
}
