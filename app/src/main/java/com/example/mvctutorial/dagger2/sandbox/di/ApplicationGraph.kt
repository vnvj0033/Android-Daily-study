package com.example.mvctutorial.dagger2.sandbox.di

import dagger.Component

@Component
interface ApplicationGraph {

    fun repository(): UserRepository
}