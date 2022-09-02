package com.example.mvctutorial.di.sandbox.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideLoginRetrofitService(
        okHttpClient: OkHttpClient
    ) {}
}