package com.example.testtasknetronic.presentation.ui.historyscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.domain.usecases.GetHistoryUseCase
import com.example.testtasknetronic.utils.Resource
import com.example.testtasknetronic.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {
    private val _historyUsers: MutableLiveData<Resource<List<UserModel>>> = MutableLiveData()
    val historyUsers: LiveData<Resource<List<UserModel>>> = _historyUsers

    private val _userModelClicked: MutableLiveData<UserModel> = SingleLiveEvent()
    val userModelClicked: LiveData<UserModel> = _userModelClicked

    init {
        getHistoryUsers()
    }

    private fun getHistoryUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _historyUsers.postValue(Resource.loading(null))
            val list = getHistoryUseCase.execute()
            _historyUsers.postValue(list)
        }
    }

    fun clickUser(model: UserModel) {
        _userModelClicked.value = model
    }
}