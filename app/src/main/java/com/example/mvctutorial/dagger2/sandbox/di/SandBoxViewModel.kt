package com.example.mvctutorial.dagger2.sandbox.di

import javax.inject.Inject

class SandBoxViewModel @Inject constructor(
    private val userRepository: UserRepository
)