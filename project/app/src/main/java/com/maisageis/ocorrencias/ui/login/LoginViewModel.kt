package com.maisageis.ocorrencias.ui.login

import androidx.lifecycle.*
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.LoginRequest
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.repository.UserRepository
import kotlinx.coroutines.*

sealed class LoginViewAction{
    open class Success(val item: UserResponse): LoginViewAction()
    open class Loading(val loading: Boolean): LoginViewAction()
    open class Error(val item: ErrorResponse): LoginViewAction()
    open class SuccessEmail(val item: Boolean): LoginViewAction()
    open class ErrorEmail(val item: ErrorResponse): LoginViewAction()
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

    fun loginPassword(value: String) {
        _actionView.postValue(LoginViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeLoginEmail(value)
        }
    }

    private suspend fun executeLogin(value: LoginRequest) {
        viewModelScope.async(Dispatchers.IO) {
            return@async userRepository.loginUser(value, ::showSuccess, ::showError)
        }.await()
    }

    private suspend fun executeLoginEmail(value: String) {
        viewModelScope.async(Dispatchers.IO) {
            return@async userRepository.loginPassword(UserRequest(email = value), ::showSuccessEmail, ::showErrorEmail)
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

    private fun showErrorEmail(error: ErrorResponse) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.ErrorEmail(error))
    }

    private fun showSuccessEmail(item: Boolean) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.SuccessEmail(item))
    }
}