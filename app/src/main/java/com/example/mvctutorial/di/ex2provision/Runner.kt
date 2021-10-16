package com.example.mvctutorial.di.ex2provision


fun main() {
    val person = DaggerPersonComponent.create()
    print(person.getString())
    print(person.getStudent().name)
}