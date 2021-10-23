package com.example.mvctutorial.di.ex8scope

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getApplication() : Application
}
