package com.example.testtasknetronic.domain.usecases

import com.example.testtasknetronic.domain.repository.UserRepository
import javax.inject.Inject

class GetNightModeUseCase @Inject constructor(private val repository: UserRepository) {
    fun execute(): Int {
        val resVal = repository.getNightMode()
        return resVal
    }
}