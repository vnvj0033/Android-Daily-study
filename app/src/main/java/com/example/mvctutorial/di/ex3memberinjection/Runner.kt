package com.example.mvctutorial.di.ex3memberinjection

fun main() {
    val personComponent = DaggerSchoolComponent.create()
    val student = Student()
    val teacher = Teacher()
    personComponent.injectStudent(student)
    personComponent.injectTeacher(teacher)
    println(student.name)
    println(teacher.name)
}

