package com.example.mvctutorial.di_pre.ex9customscope

import dagger.Component

@CustomScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getApplication(): Application
}
