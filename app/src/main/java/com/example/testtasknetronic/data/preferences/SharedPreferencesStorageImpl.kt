package com.example.testtasknetronic.data.preferences

import android.content.Context

private const val PREFERENCES_NAME = "m_preferences"
private const val PREFERENCES_NIGHT_MODE = "night_mode_preferences"

class SharedPreferencesStorageImpl(context: Context) : SharedPreferencesStorage {
    private val preferencesStorage =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun getNightMode(): Int {
        val retVal = preferencesStorage.getInt(
            PREFERENCES_NIGHT_MODE,
            SharedPreferencesStorage.NIGHT_MODE_SYSTEM
        )
        return retVal
    }

    override fun saveNightMode(mode: Int) {
        preferencesStorage.edit()
            .putInt(PREFERENCES_NIGHT_MODE, mode).apply()
    }
}