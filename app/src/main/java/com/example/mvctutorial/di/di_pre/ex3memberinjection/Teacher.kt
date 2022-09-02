package com.example.mvctutorial.di.di_pre.ex3memberinjection

import javax.inject.Inject

class Teacher {
    lateinit var name: String
    @Inject
    fun setTeacherName(name: String) {
        this.name = "$name method inject"
    }
}
