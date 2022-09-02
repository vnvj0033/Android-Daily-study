package com.example.mvctutorial.di.di_pre.ex10subcomponent

import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
interface FragmentSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): FragmentSubComponent
    }
}
