package com.example.testtasknetronic.di

import dagger.Component
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent


@ActivityScoped
@EntryPoint
@InstallIn(SingletonComponent::class)
@Component(
    modules = [AppModule::class, NetworkModule::class],
)
interface AppComponent {
}
