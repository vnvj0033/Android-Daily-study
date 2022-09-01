package com.example.mvctutorial.dagger2.sandbox.di

import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface LoginComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(loginActivity: LoginActivity)
}