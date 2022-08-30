package com.example.mvctutorial.dagger2.sandbox

import dagger.Component

@Component
interface AppComponent {

    fun inject(activity: SandBoxActivity)
}