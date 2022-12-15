package com.example.testtasknetronic.domain.usecases

import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.domain.model.mapToDomain
import com.example.testtasknetronic.domain.repository.UserRepository
import com.example.testtasknetronic.utils.Resource
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun execute(): Resource<List<UserModel>> {
        val res = try {
            val resVal = repository.getUsers(5)//todo implement random number
            if (resVal.isSuccessful) {
                val listUsers = resVal.body()?.results?.map { it.mapToDomain() } ?: emptyList()
                Resource.success(listUsers)
            } else {
                Resource.error(null, "Something went wrong")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Resource.error(null, ex.message)
        }
        return res
    }
}