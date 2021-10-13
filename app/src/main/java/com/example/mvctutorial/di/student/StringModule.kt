package com.example.mvctutorial.di.student

import dagger.Module
import dagger.Provides

@Module
class StringModule {
    @Provides
    fun providesString() = "StringA"
}