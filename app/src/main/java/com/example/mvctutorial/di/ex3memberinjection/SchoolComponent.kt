package com.example.mvctutorial.di.ex3memberinjection

import dagger.Component

@Component(modules = [PersonModule::class])
interface SchoolComponent {
    fun injectStudent(student: Student)
    fun injectTeacher(teacher: Teacher)
}
