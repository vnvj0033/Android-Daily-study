package com.example.mvctutorial.di_pre.ex2provision

import dagger.Module
import dagger.Provides

@Module
class PersonModule {
    @Provides
    fun providesString() = "Example2 Student"
}
