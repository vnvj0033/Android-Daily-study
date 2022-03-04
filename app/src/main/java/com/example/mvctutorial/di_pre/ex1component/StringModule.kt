package com.example.mvctutorial.di_pre.ex1component

import dagger.Module
import dagger.Provides

@Module
class StringModule {
    @Provides
    fun providesString() = "StringA"
}
