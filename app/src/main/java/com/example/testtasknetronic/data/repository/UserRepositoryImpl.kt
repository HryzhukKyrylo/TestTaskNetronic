package com.example.testtasknetronic.data.repository

import com.example.testtasknetronic.data.api.ApiService
import com.example.testtasknetronic.data.localstorage.LocalStorage
import com.example.testtasknetronic.data.model.UserResponse
import com.example.testtasknetronic.data.model.toDTO
import com.example.testtasknetronic.data.model.toModel
import com.example.testtasknetronic.data.preferences.SharedPreferencesStorage
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localStorage: LocalStorage,
    private val preferencesStorage: SharedPreferencesStorage,
) : UserRepository {
    override suspend fun getUsers(number: Int): Response<UserResponse> {
        return apiService.getUsers(number = number)
    }

    override suspend fun saveListUsers(data: List<UserModel>) {
        localStorage.saveListUsers(
            data.map { it.toDTO() }
        )
    }

    override suspend fun getListHistory(): List<UserModel>? {
        return localStorage.getListHistory()?.map { it.toModel() }
    }


    override fun getNightMode(): Int {
        val resVal = preferencesStorage.getNightMode()
        return resVal
    }

    override fun saveNightMode(mode: Int) {
        preferencesStorage.saveNightMode(mode = mode)
    }

}