package com.example.mvctutorial.di.ex7nonscope

import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getApplication() : Application
}
