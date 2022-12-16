package com.example.testtasknetronic.data.localstorage

import com.example.testtasknetronic.data.model.UserDTO
import javax.inject.Inject

interface LocalStorage {
    fun saveListUsers(data: List<UserDTO>)
    fun getListHistory(): List<UserDTO>
}

class LocalStorageImpl @Inject constructor(
    private val database: UserDatabase
) : LocalStorage {
    override fun getListHistory(): List<UserDTO> {
        return database.userDao().getAll()
    }

    override fun saveListUsers(data: List<UserDTO>) {
        database.userDao().saveListUsers(data)
    }
}