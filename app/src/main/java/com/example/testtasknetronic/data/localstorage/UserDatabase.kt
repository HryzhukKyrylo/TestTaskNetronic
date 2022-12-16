package com.example.testtasknetronic.data.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtasknetronic.data.model.UserDTO

@Database(entities = [UserDTO::class], version = 1, exportSchema = false)
abstract class UserDatabase :RoomDatabase(){
    abstract fun userDao(): UserDao
}
