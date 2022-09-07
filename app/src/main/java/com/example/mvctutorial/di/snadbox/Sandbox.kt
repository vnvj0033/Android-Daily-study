package com.example.mvctutorial.di.snadbox

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

fun main() {
    // inject(), Member-injection Method 실행 (coffeeMaker객체의 멤버변수/필드에 의존성 주입)
    val component = DaggerCoffeeComponent.create()

    val coffeeMaker = component.inject()

    coffeeMaker.brew()
}



@Component(modules = [CoffeeMakerModule::class])
interface CoffeeComponent {

    // member-injection method 유형
    fun inject(): CoffeeMaker
}

@Module
class CoffeeMakerModule {
    @Provides
    fun provideHeater() : Heater = object : Heater {
        private var heating : Boolean = false
        override fun on() { heating = true }
        override fun off() { heating = false }
        override fun isHot(): Boolean = heating
    }

    @Provides
    fun providePump(heater: Heater) : Pump = object : Pump {
        override fun pump() {
            if (heater.isHot()) {
                println("coffeMaker" + "pumping")
            }
        }

    }

    @Provides
    fun provideCoffeeMaker(heater: Heater, pump: Pump) = CoffeeMaker(heater, pump)
}

class CoffeeMaker(
    private var heater: Heater,
    private var pump: Pump
) {
    fun brew() {
        heater.on()
        pump.pump()
        println("coffeMaker" + "[_]P coffee! [_]P")
        heater.off()
    }
}

interface Heater {
    fun on()
    fun off()
    fun isHot() : Boolean
}

interface Pump {
    fun pump()
}