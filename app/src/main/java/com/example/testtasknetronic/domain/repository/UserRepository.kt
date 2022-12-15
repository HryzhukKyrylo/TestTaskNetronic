package com.example.testtasknetronic.domain.repository

import com.example.testtasknetronic.data.model.UserResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(number: Int): Response<UserResponse>
}