package com.example.mvctutorial.di.di_pre.ex4namedannotation

import javax.inject.Inject
import javax.inject.Named

class Student {
    @Inject
    @Named("Student")
    lateinit var name: String


    @Inject
    @Named("Address")
    lateinit var address: String
}
