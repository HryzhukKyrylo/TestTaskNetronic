package com.example.testtasknetronic.data.repository

import com.example.testtasknetronic.data.api.ApiService
import com.example.testtasknetronic.data.model.UserResponse
import com.example.testtasknetronic.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(number: Int): Response<UserResponse> {
        return apiService.getUsers(number = number)
    }
}