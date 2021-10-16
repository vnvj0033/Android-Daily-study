package com.example.mvctutorial.di.ex1component

fun main() {
    val person = Student()
    DaggerStringComponent.create().injectString(person)
    println (person.name)
}
