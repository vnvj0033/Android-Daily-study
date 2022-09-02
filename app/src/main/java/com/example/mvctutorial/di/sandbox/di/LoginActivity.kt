package com.example.mvctutorial.di.sandbox.di

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvctutorial.App
import javax.inject.Inject

class LoginActivity: AppCompatActivity() {

    @Inject lateinit var loginComponent: LoginComponent
    @Inject lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        loginComponent = (application as App).appComponent.loginComponent().create()
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)

    }
}