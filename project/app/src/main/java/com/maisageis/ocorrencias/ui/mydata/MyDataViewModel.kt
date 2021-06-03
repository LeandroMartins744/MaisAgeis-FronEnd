package com.maisageis.ocorrencias.ui.mydata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.repository.StreetRepository
import com.maisageis.ocorrencias.repository.UserRepository
import com.maisageis.ocorrencias.ui.action.SearchCepViewAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MyDataViewModel(
    private val streetRepository: StreetRepository,
    private val userRepository: UserRepository
): ViewModel() {
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
}