package com.example.testtasknetronic.data.localstorage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testtasknetronic.data.model.UserDTO

@Dao
interface UserDao {
    @Insert
    fun saveListUsers(user: List<UserDTO>)

    @Query("SELECT * FROM my_user_table")
    fun getAll(): List<UserDTO>

    @Query("DELETE FROM my_user_table")
    fun deleteAll()
}