package com.example.mvctutorial.di.person

import dagger.Module
import dagger.Provides

@Module
class PersonModule {
    @Provides
    fun providesString() = "Example2 Student"
}
