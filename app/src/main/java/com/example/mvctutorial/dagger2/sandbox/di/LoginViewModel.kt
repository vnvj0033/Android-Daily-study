package com.example.mvctutorial.dagger2.sandbox.di

import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
)