package com.example.mvctutorial.di.ex5bind

import dagger.Component

@Component(modules = [PersonModule::class])
interface SchoolComponent {
    fun getStudent(): Student
}
