package com.example.mvctutorial.di.ex2provision

import dagger.Component

@Component(modules = [PersonModule::class])
interface PersonComponent {
    fun getString(): String
    fun getStudent(): Student
}
