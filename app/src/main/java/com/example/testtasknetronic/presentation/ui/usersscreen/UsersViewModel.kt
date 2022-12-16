package com.example.testtasknetronic.presentation.ui.usersscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasknetronic.data.preferences.SharedPreferencesStorage
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.domain.usecases.GetNightModeUseCase
import com.example.testtasknetronic.domain.usecases.GetUsersUseCase
import com.example.testtasknetronic.domain.usecases.SaveNightModeUseCase
import com.example.testtasknetronic.utils.Resource
import com.example.testtasknetronic.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getNightModeUseCase: GetNightModeUseCase,
    private val saveNightModeUseCase: SaveNightModeUseCase,
) : ViewModel() {
    private val _userResponse: MutableLiveData<Resource<List<UserModel>>> = MutableLiveData()
    val userResponse: LiveData<Resource<List<UserModel>>> = _userResponse

    private val _userClicked: MutableLiveData<UserModel> = SingleLiveEvent()
    val userClicked: LiveData<UserModel> = _userClicked

    private val _isNightMode: MutableLiveData<Boolean> = MutableLiveData(false)
    val isNightMode: LiveData<Boolean> = _isNightMode


    init {
        checkNightMode()
    }

    private fun checkNightMode() {
        viewModelScope.launch(Dispatchers.IO) {
            val nightMode = getNightModeUseCase.execute()
            val isNightModeOn = verifyNightModeOn(nightMode)
            _isNightMode.postValue(isNightModeOn)
            withContext(Dispatchers.Main) {
                saveNightModeUseCase.execute(nightMode)
            }
        }
    }

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

    fun switchNightMode() {
        viewModelScope.launch(Dispatchers.IO) {
            val isNightModeOn = _isNightMode.value ?: false
            _isNightMode.postValue(!isNightModeOn)
            val nightMode = if (!isNightModeOn) SharedPreferencesStorage.NIGHT_MODE_ON
            else SharedPreferencesStorage.NIGHT_MODE_OFF
            withContext(Dispatchers.Main) {
                saveNightModeUseCase.execute(nightMode)
            }
        }
    }

    private fun verifyNightModeOn(resVal: Int): Boolean {
        val resVal = when (resVal) {
            SharedPreferencesStorage.NIGHT_MODE_ON -> {
                true
            }
            SharedPreferencesStorage.NIGHT_MODE_OFF -> {
                false
            }
            else -> {
                false
            }
        }
        return resVal
    }
}