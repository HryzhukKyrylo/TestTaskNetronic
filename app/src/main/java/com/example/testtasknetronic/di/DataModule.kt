package com.example.testtasknetronic.di

import android.content.Context
import androidx.room.Room
import com.example.testtasknetronic.data.localstorage.UserDatabase
import com.example.testtasknetronic.data.preferences.SharedPreferencesStorage
import com.example.testtasknetronic.data.preferences.SharedPreferencesStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "private_user_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesStorage(@ApplicationContext appContext: Context): SharedPreferencesStorage {
        return SharedPreferencesStorageImpl(appContext)
    }

}