package com.example.mvctutorial.di.ex3memberinjection

import javax.inject.Inject

class Student {
    @Inject
    lateinit var name: String
}