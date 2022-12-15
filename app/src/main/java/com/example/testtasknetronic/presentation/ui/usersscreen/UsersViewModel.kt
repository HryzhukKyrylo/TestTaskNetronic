package com.example.testtasknetronic.presentation.ui.usersscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.domain.usecases.GetUsersUseCase
import com.example.testtasknetronic.utils.Resource
import com.example.testtasknetronic.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _userResponse: MutableLiveData<Resource<List<UserModel>>> = MutableLiveData()
    val userResponse: LiveData<Resource<List<UserModel>>> = _userResponse

    private val _userClicked: MutableLiveData<UserModel> = SingleLiveEvent()
    val userClicked: LiveData<UserModel> = _userClicked

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _userResponse.postValue(Resource.loading(null))
            val test = getUsersUseCase.execute()
            _userResponse.postValue(test)
        }
    }

    fun clickUser(item: UserModel) {
        _userClicked.value = item
    }
}