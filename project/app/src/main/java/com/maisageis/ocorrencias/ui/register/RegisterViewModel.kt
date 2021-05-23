package com.maisageis.ocorrencias.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.repository.StreetRepository
import com.maisageis.ocorrencias.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

sealed class RegisterViewAction{
    open class Success(val item: UserResponse): RegisterViewAction()
    open class Loading(val loading: Boolean): RegisterViewAction()
    open class Error(val item: ErrorResponse): RegisterViewAction()
}

sealed class SearchCepViewAction{
    open class Success(val item: StreetCepResponse): SearchCepViewAction()
    open class Loading(val loading: Boolean): SearchCepViewAction()
    open class Error(val item: ErrorResponse): SearchCepViewAction()
}

class RegisterViewModel(
    private val streetRepository: StreetRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private val _actionView by lazy { MutableLiveData<RegisterViewAction>() }
    val actionView: LiveData<RegisterViewAction> get() = _actionView

    private val _actionCepSearchView by lazy { MutableLiveData<SearchCepViewAction>() }
    val actionCepSearchView: LiveData<SearchCepViewAction> get() = _actionCepSearchView

    fun cepSearch(cep: String){
        _actionCepSearchView.postValue(SearchCepViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeCepSearch(cep)
        }
    }

    private suspend fun executeCepSearch(cep: String){
        viewModelScope.async(Dispatchers.IO){
            return@async streetRepository.cepSearchStreet(StreetCepRequest(cep), ::showSuccessCep, ::showErrorCep)
        }
    }

    private fun showErrorCep(error: ErrorResponse) {
        _actionCepSearchView.postValue(SearchCepViewAction.Loading(false))
        _actionCepSearchView.postValue(SearchCepViewAction.Error(error))
    }

    private fun showSuccessCep(item: StreetCepResponse) {
        _actionCepSearchView.postValue(SearchCepViewAction.Loading(false))
        _actionCepSearchView.postValue(SearchCepViewAction.Success(item))
    }

    fun register(value: UserRequest){
        _actionView.postValue(RegisterViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeRegister(value)
        }
    }

    private suspend fun executeRegister(value: UserRequest){
        viewModelScope.async(Dispatchers.IO){
            return@async userRepository.insertUser(value, ::showSuccess, ::showError)
        }
    }

    private fun showError(error: ErrorResponse) {
        _actionView.postValue(RegisterViewAction.Loading(false))
        _actionView.postValue(RegisterViewAction.Error(error))
    }

    private fun showSuccess(item: UserResponse) {
        _actionView.postValue(RegisterViewAction.Loading(false))
        _actionView.postValue(RegisterViewAction.Success(item))
    }
}