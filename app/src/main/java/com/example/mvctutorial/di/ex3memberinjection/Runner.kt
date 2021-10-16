package com.example.mvctutorial.di.ex3memberinjection

fun main() {
    val personComponent = DaggerSchoolComponent.create()
    val student = Student()
    personComponent.injectStudent(student)
    println(student.name)
}

