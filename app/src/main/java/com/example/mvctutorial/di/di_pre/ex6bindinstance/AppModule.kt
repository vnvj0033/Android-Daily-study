package com.example.mvctutorial.di.di_pre.ex6bindinstance

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun providesApplication(name: String) = Application(name)
}
