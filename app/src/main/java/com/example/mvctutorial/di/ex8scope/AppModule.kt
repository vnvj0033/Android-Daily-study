package com.example.mvctutorial.di.ex8scope

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun providesApplication() = Application()
}