package com.example.mvctutorial.di.sandbox.di

import javax.inject.Inject

class SandBoxViewModel @Inject constructor(
    private val userRepository: UserRepository
)