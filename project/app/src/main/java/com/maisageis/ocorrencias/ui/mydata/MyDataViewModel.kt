package com.maisageis.ocorrencias.ui.mydata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.repository.StreetRepository
import com.maisageis.ocorrencias.repository.UserRepository
import com.maisageis.ocorrencias.ui.action.SearchCepViewAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

sealed class UserActionView{
    open class Success(val item: Boolean): UserActionView()
    open class Loading(val loading: Boolean): UserActionView()
    open class Error(val item: ErrorResponse): UserActionView()
}

class MyDataViewModel(
    private val streetRepository: StreetRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _actionCepSearchView by lazy { MutableLiveData<SearchCepViewAction>() }
    val actionCepSearchView: LiveData<SearchCepViewAction> get() = _actionCepSearchView

    private val _actionUserView by lazy { MutableLiveData<UserActionView>() }
    val actionUserView: LiveData<UserActionView> get() = _actionUserView

    fun cepSearch(cep: String){
        _actionCepSearchView.postValue(SearchCepViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeCepSearch(cep)
        }
    }

    fun alterUser(value: UserRequest){
        _actionCepSearchView.postValue(SearchCepViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeAlterUser(value)
        }
    }

    private suspend fun executeAlterUser(value: UserRequest){
        viewModelScope.async(Dispatchers.IO){
            return@async userRepository.updateUser(value, ::showSuccess, ::showError)
        }
    }
    private fun showError(error: ErrorResponse) {
        _actionUserView.postValue(UserActionView.Loading(false))
        _actionUserView.postValue(UserActionView.Error(error))
    }

    private fun showSuccess(item: Boolean) {
        _actionUserView.postValue(UserActionView.Loading(false))
        _actionUserView.postValue(UserActionView.Success(item))
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
}