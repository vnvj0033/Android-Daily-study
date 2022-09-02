package com.example.mvctutorial.di.di_pre.ex2provision


fun main() {
    val personComponent = DaggerPersonComponent.create()
    val string = personComponent.getString() // Example2 Student
    val student = personComponent.getStudent()
    println(string)
    println(student.name)
}
