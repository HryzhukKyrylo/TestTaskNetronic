package com.example.testtasknetronic.domain.usecases

import androidx.appcompat.app.AppCompatDelegate
import com.example.testtasknetronic.data.preferences.SharedPreferencesStorage
import com.example.testtasknetronic.domain.repository.UserRepository
import javax.inject.Inject

class SaveNightModeUseCase @Inject constructor(private val repository: UserRepository) {
    fun execute(mode: Int) {
        setNightMode(mode)
        repository.saveNightMode(mode = mode)
    }

    fun setNightMode(mode: Int) {
        when (mode) {
            SharedPreferencesStorage.NIGHT_MODE_OFF -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            SharedPreferencesStorage.NIGHT_MODE_ON -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            SharedPreferencesStorage.NIGHT_MODE_SYSTEM -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}