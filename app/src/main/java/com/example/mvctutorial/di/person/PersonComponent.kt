package com.example.mvctutorial.di.person

import com.example.mvctutorial.di.student.Student
import dagger.Component

@Component(modules = [PersonModule::class])
interface PersonComponent {
    fun getString(): String
    fun getStudent(): Student
}