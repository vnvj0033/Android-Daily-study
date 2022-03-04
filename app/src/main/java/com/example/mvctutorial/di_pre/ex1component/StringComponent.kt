package com.example.mvctutorial.di_pre.ex1component

import dagger.Component

@Component(modules = [StringModule::class])
interface StringComponent {
    fun injectString(student: Student)
}
