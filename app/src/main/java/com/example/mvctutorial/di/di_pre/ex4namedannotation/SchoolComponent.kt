package com.example.mvctutorial.di.di_pre.ex4namedannotation

import dagger.Component

@Component(modules = [PersonModule::class])
interface SchoolComponent {
    fun injectStudent(student: Student)
    fun injectTeacher(teacher: Teacher)
}
