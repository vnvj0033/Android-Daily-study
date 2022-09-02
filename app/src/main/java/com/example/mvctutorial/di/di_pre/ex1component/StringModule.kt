package com.example.mvctutorial.di.di_pre.ex1component

import dagger.Module
import dagger.Provides

@Module
class StringModule {
    @Provides
    fun providesName() = "name"
    @Provides
    fun providesAddress() = 1
}
