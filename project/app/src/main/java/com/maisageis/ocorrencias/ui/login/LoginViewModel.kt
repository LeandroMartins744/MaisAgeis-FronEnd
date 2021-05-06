package com.maisageis.ocorrencias.ui.login

import androidx.lifecycle.*
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.repository.LoginRepository
import kotlinx.coroutines.*
import java.lang.Exception

sealed class LoginViewAction{
    //open class Success(val item: UserResponse): LoginViewAction()
   // open class Loading(val loading: Boolean): LoginViewAction()
   // open class Error(val item: Exception): LoginViewAction()
}

class LoginViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {

   // private val _actionView by lazy { MutableLiveData<LoginViewAction>() }
   // val actionView: LiveData<LoginViewAction> get() = _actionView

   // fun loginUser(login: String, pass: String) {
        //_actionView.postValue(LoginViewAction.Loading(true))
       // viewModelScope.launch(Dispatchers.IO) {
       //     executeLogin()
       // }
    //}

    /*private suspend fun executeLogin() {
        viewModelScope.async(Dispatchers.IO) {
            return@async loginRepository.getUsers()
        }.await().fold(::showError, ::showSuccess)
    }

    private fun showError(error: Exception) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Error(error))
    }

    private fun showSuccess(item: UserResponse) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Success(item))
    }

    class LoginViewModelFactory(
        private val loginRepository: LoginRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(loginRepository) as T
        }

    }

     */
}