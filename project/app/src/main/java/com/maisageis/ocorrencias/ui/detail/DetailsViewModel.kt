package com.maisageis.ocorrencias.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.BORelRequest
import com.maisageis.ocorrencias.model.response.CategoryResponse
import com.maisageis.ocorrencias.model.response.CoodResponse
import com.maisageis.ocorrencias.repository.BORepository
import com.maisageis.ocorrencias.ui.action.CategoryViewAction
import com.maisageis.ocorrencias.ui.action.DetailsViewAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val boRepository: BORepository
) : ViewModel(){

    private val _actionDetailsView by lazy { MutableLiveData<DetailsViewAction>() }
    val actionDetailsView: LiveData<DetailsViewAction> get() = _actionDetailsView

    private val _actionCategoryView by lazy { MutableLiveData<CategoryViewAction>() }
    val actionCategoryView: LiveData<CategoryViewAction> get() = _actionCategoryView

    fun getDetails(value: BORelRequest) {
        _actionDetailsView.postValue(DetailsViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeDetails(value)
        }
    }

    fun getCategory() {
        _actionCategoryView.postValue(CategoryViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeCategory()
        }
    }

    private suspend fun executeDetails(value: BORelRequest) {
        viewModelScope.async(Dispatchers.IO) {
            return@async boRepository.getDetails(value, ::showSuccess, ::showError)
        }.await()
    }

    private fun showError(error: ErrorResponse) {
        _actionDetailsView.postValue(DetailsViewAction.Loading(false))
        _actionDetailsView.postValue(DetailsViewAction.Error(error))
    }

    private fun showSuccess(item: CoodResponse) {
        _actionDetailsView.postValue(DetailsViewAction.Loading(false))
        _actionDetailsView.postValue(DetailsViewAction.Success(item))
    }

    private suspend fun executeCategory() {
        viewModelScope.async(Dispatchers.IO) {
            return@async boRepository.geCategory(::showCategorySuccess, ::showCategoryError)
        }.await()
    }

    private fun showCategoryError(error: ErrorResponse) {
        _actionCategoryView.postValue(CategoryViewAction.Loading(false))
        _actionCategoryView.postValue(CategoryViewAction.Error(error))
    }

    private fun showCategorySuccess(item: List<CategoryResponse>) {
        _actionCategoryView.postValue(CategoryViewAction.Loading(false))
        _actionCategoryView.postValue(CategoryViewAction.Success(item))
    }
}