package com.example.mvctutorial.di.ex3memberinjection

import dagger.Module
import dagger.Provides

@Module
class PersonModule {
    @Provides
    fun providesString() = "Example3 Name"
}
