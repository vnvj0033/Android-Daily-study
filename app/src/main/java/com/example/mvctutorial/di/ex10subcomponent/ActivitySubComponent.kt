package com.example.mvctutorial.di.ex10subcomponent

import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivitySubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ActivitySubComponent
    }
}
