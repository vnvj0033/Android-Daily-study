package com.example.mvctutorial.di_pre.ex5bind

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PersonModule {
    @Binds
    abstract fun providesStudentWithName(student: Student): Person

    companion object {
        @Provides
        fun providesString() = "StudentA"
    }
}
