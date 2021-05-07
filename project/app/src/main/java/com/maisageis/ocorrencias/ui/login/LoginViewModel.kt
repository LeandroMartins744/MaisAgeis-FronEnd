package com.maisageis.ocorrencias.ui.login

import androidx.lifecycle.*
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.repository.LoginRepository
import kotlinx.coroutines.*
import java.lang.Exception

sealed class LoginViewAction{
    open class Success(val item: UserResponse): LoginViewAction()
    open class Loading(val loading: Boolean): LoginViewAction()
    open class Error(val item: ErrorResponse): LoginViewAction()
}

class LoginViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {

    private val _actionView by lazy { MutableLiveData<LoginViewAction>() }
    val actionView: LiveData<LoginViewAction> get() = _actionView

    fun loginUser(login: String, pass: String) {
        _actionView.postValue(LoginViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeLogin(login, pass)
        }
    }

    private suspend fun executeLogin(login: String, pass: String) {
        viewModelScope.async(Dispatchers.IO) {
            return@async loginRepository.getUsers(login, pass, ::showSuccess, ::showError)
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