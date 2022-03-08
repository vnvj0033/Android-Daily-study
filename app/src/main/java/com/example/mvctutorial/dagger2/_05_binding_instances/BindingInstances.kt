package com.example.mvctutorial.dagger2._05_binding_instances

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class HeroModule {

    @Provides
    @Named("hulk")
    fun provideHulk(): Person = Hulk()

    @Provides
    @Named("heroHulkWihWeapon")
    fun provideHeroHulkWithWeapon(@Named("hulk") person: Person, weapon: Weapon) = Hero(person, weapon)
}


@Component(modules = [HeroModule::class])
interface HeroComponent {
    // 헐크용 Hero 객체 생성 함수 추가
    @Named("heroHulkWihWeapon")
    fun callHulkWithWeapon(): Hero

    // weapon을 넘겨받기 위한 builder 추가
    @Component.Builder
    interface Builder {
        fun setHulkWeapon(@BindsInstance hulkWeaponProvider: Weapon): Builder
        fun build(): HeroComponent
    }
}

fun main() {
    val hulkBuster = HulkBuster()
    val hulkWithWeapon = DaggerHeroComponent.builder().setHulkWeapon(hulkBuster).build().callHulkWithWeapon()

}
