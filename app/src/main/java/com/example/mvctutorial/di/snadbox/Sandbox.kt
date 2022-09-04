package com.example.mvctutorial.di.snadbox

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

fun main() {
    DaggerCoffeeComponent.create().make().brew()
}

@Module
class CoffeeMakerModule {
    @Provides
    fun provideHeater() : Heater = object : Heater {

        private var heating : Boolean = false

        override fun on() {
            heating = true
        }

        override fun off() {
            heating = false
        }

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
}


@Component(modules = [CoffeeMakerModule::class])
interface CoffeeComponent {

    // provision method 유형
    fun make() : CoffeeMaker
}

class CoffeeMaker @Inject constructor(
    private val heater: Heater,
    private val pump: Pump
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