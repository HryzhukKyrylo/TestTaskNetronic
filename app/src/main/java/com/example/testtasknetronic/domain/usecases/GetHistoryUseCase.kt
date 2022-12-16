package com.example.testtasknetronic.domain.usecases

import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.domain.repository.UserRepository
import com.example.testtasknetronic.utils.Resource
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun execute(): Resource<List<UserModel>> {
        val retVal = try {
            val list = repository.getListHistory() ?: emptyList()
            Resource.success(list)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Resource.error(null, ex.message)
        }
        return retVal
    }
}