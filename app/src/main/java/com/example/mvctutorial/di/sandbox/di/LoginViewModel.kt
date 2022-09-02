package com.example.mvctutorial.di.sandbox.di

import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
)