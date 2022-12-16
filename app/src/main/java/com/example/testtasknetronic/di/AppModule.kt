package com.example.testtasknetronic.di

import com.example.testtasknetronic.data.api.ApiService
import com.example.testtasknetronic.data.localstorage.LocalStorage
import com.example.testtasknetronic.data.localstorage.LocalStorageImpl
import com.example.testtasknetronic.data.localstorage.UserDatabase
import com.example.testtasknetronic.data.repository.UserRepositoryImpl
import com.example.testtasknetronic.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService, localStorage: LocalStorage): UserRepository =
        UserRepositoryImpl(apiService, localStorage)


    @Provides
    @Singleton
    fun provideLocalStorage(userDatabase: UserDatabase): LocalStorage {
        return LocalStorageImpl(userDatabase)
    }
}