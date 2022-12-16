package com.example.testtasknetronic.data.preferences


interface SharedPreferencesStorage {
    fun getNightMode(): Int
    fun saveNightMode(mode: Int)

    companion object {
        const val NIGHT_MODE_ON = 1
        const val NIGHT_MODE_OFF = 2
        const val NIGHT_MODE_SYSTEM = 0
    }
}