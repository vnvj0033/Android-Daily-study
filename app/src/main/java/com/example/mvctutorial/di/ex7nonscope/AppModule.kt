package com.example.mvctutorial.di.ex7nonscope

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun providesApplication() = Application()
}
