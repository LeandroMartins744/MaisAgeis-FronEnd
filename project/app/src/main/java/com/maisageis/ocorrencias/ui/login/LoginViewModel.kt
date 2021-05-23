package com.maisageis.ocorrencias.ui.login

import androidx.lifecycle.*
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.LoginRequest
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.repository.UserRepository
import kotlinx.coroutines.*

sealed class LoginViewAction{
    open class Success(val item: UserResponse): LoginViewAction()
    open class Loading(val loading: Boolean): LoginViewAction()
    open class Error(val item: ErrorResponse): LoginViewAction()
}

class LoginViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    private val _actionView by lazy { MutableLiveData<LoginViewAction>() }
    val actionView: LiveData<LoginViewAction> get() = _actionView

    fun loginUser(value: LoginRequest) {
        _actionView.postValue(LoginViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeLogin(value)
        }
    }

    private suspend fun executeLogin(value: LoginRequest) {
        viewModelScope.async(Dispatchers.IO) {
            return@async userRepository.loginUser(value, ::showSuccess, ::showError)
        }.await()
    }

    private fun showError(error: ErrorResponse) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Error(error))
    }

    private fun showSuccess(item: UserResponse) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Success(item))
    }
}