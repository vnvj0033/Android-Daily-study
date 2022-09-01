package com.example.mvctutorial.dagger2.sandbox.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideLoginRetrofitService(
        okHttpClient: OkHttpClient
    ) {}
}