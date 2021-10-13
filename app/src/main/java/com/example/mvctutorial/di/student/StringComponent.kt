package com.example.mvctutorial.di.student

import dagger.Component

@Component(modules = [StringModule::class])
interface StringComponent {
    fun injectString(student: Student)
}