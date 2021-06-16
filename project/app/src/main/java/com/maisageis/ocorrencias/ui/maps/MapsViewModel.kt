package com.maisageis.ocorrencias.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.BORelRequest
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.getSearchStreet
import com.maisageis.ocorrencias.repository.BORepository
import com.maisageis.ocorrencias.repository.StreetRepository
import com.maisageis.ocorrencias.ui.action.SearchCepViewAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

sealed class SearchCityViewAction{
    open class Success(val item: List<getSearchStreet>): SearchCityViewAction()
    open class Loading(val loading: Boolean): SearchCityViewAction()
    open class Error(val item: ErrorResponse): SearchCityViewAction()
}

class MapsViewModel(
    private val streetRepository: StreetRepository,
    private val boRepository: BORepository
):ViewModel() {
    private val _actionCepSearchView by lazy { MutableLiveData<SearchCepViewAction>() }
    val actionCepSearchView: LiveData<SearchCepViewAction> get() = _actionCepSearchView

    private val _actionCityView by lazy { MutableLiveData<SearchCityViewAction>() }
    val actionCityView: LiveData<SearchCityViewAction> get() = _actionCityView

    fun cepSearch(cep: String){
        _actionCepSearchView.postValue(SearchCepViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeCepSearch(cep)
        }
    }

    fun citySearch(city: String){
        _actionCepSearchView.postValue(SearchCepViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeCitySearch(city)
        }
    }
    private suspend fun executeCitySearch(city: String){
        viewModelScope.async(Dispatchers.IO){
            return@async boRepository.getCity(BORelRequest(City = city), ::showSuccess, ::showError)
        }
    }

    private fun showError(error: ErrorResponse) {
        _actionCityView.postValue(SearchCityViewAction.Loading(false))
        _actionCityView.postValue(SearchCityViewAction.Error(error))
    }

    private fun showSuccess(item: List<getSearchStreet>) {
        _actionCityView.postValue(SearchCityViewAction.Loading(false))
        _actionCityView.postValue(SearchCityViewAction.Success(item))
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