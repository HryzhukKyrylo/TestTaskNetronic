package com.example.testtasknetronic.presentation.ui.usersscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(

) : ViewModel() {

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            //todo implement get users
        }
    }
}