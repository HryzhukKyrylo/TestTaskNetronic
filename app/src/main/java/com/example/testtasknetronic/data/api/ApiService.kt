package com.example.testtasknetronic.data.api

import com.example.testtasknetronic.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    suspend fun getUsers(@Query(value = "results")number: Int): Response<UserResponse>
}