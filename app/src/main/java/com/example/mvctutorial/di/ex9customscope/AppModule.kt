package com.example.mvctutorial.di.ex9customscope

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @CustomScope
    @Provides
    fun providesApplication() = Application()
}
