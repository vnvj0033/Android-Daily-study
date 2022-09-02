package com.example.mvctutorial.di.di_pre.ex7nonscope

import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getApplication() : Application
}
