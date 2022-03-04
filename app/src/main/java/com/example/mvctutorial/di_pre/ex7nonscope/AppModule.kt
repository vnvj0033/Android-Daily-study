package com.example.mvctutorial.di_pre.ex7nonscope

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun providesApplication() = Application()
}
