package com.example.mvctutorial.di_pre.ex4namedannotation

import javax.inject.Inject
import javax.inject.Named

class Teacher {
    @Inject
    @Named("Teacher")
    lateinit var name: String
}
