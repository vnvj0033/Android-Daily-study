package com.example.mvctutorial.di.ex4namedannotation

import javax.inject.Inject
import javax.inject.Named

class Student {
    @Inject
    @Named("Student")
    lateinit var name: String
}
