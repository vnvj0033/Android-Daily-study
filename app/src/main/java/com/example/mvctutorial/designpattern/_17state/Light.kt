package com.example.mvctutorial.designpattern._17state

open class Light {

    protected val onState : LightState = object : LightState {
        override fun on() = println("NOTING")

        override fun off() {
            println("Light OFF")
            lightState = offState
        }

    }
    protected val offState : LightState = object : LightState {
        override fun on() {
            println("Light ON")
            lightState = onState
        }

        override fun off() = println("NOTING")
    }

    private var lightState = offState

    fun on() = lightState.on()
    fun off() = lightState.off()

}

interface LightState {
    fun on()
    fun off()
}