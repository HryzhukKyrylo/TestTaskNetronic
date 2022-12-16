package com.example.testtasknetronic.data.repository

import com.example.testtasknetronic.data.api.ApiService
import com.example.testtasknetronic.data.localstorage.LocalStorage
import com.example.testtasknetronic.data.model.UserResponse
import com.example.testtasknetronic.data.model.toDTO
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localStorage: LocalStorage,
) : UserRepository {
    override suspend fun getUsers(number: Int): Response<UserResponse> {
        return apiService.getUsers(number = number)
    }
    override suspend fun saveListUsers(data: List<UserModel>) {
        localStorage.saveListUsers(
            data.map { it.toDTO() }
        )
    }
}