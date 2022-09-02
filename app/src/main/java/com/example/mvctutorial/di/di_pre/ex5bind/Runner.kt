package com.example.mvctutorial.di.di_pre.ex5bind

fun main() {
    val personComponent = DaggerSchoolComponent.create()
    val student: Person = personComponent.getStudent()
    println(student.getPersonName())
}
