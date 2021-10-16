package com.example.mvctutorial.di.ex1component

import dagger.Module
import dagger.Provides

@Module
class StringModule {
    @Provides
    fun providesString() = "StringA"
}