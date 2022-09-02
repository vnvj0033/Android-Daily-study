package com.example.mvctutorial.di.di_pre.ex3memberinjection

import dagger.Component

@Component(modules = [PersonModule::class])
interface SchoolComponent {
    fun injectStudent(student: Student)
    fun injectTeacher(teacher: Teacher)
}
