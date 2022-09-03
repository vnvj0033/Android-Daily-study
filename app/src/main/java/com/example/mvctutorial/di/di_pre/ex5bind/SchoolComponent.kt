package com.example.mvctutorial.di.di_pre.ex5bind

import dagger.Component

@Component(modules = [PersonModule::class])
interface SchoolComponent {
    fun getStudent(): Person
}
