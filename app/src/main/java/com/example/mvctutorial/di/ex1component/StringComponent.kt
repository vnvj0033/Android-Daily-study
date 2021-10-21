package com.example.mvctutorial.di.ex1component

import dagger.Component

@Component(modules = [StringModule::class])
interface StringComponent {
    fun injectString(student: Student)
}
