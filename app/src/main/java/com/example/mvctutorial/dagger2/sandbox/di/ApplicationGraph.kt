package com.example.mvctutorial.dagger2.sandbox.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationGraph {

    fun repository(): UserRepository
}