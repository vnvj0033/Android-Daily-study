package com.example.mvctutorial.dagger2.sandbox.di

import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
)

class UserLocalDataSource @Inject constructor()
class UserRemoteDataSource @Inject constructor()