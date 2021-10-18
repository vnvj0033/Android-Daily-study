package com.example.mvctutorial.di.ex4namedannotation

import dagger.Component

@Component(modules = [PersonModule::class])
interface SchoolComponent {
    fun injectStudent(student: Student)
    fun injectTeacher(teacher: Teacher)
}
