package com.maisageis.ocorrencias.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.CategoryResponse
import com.maisageis.ocorrencias.repository.BORepository
import com.maisageis.ocorrencias.ui.action.CategoryViewAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(
    private val boRepository: BORepository
):ViewModel(){
    private val _actionCategoryView by lazy { MutableLiveData<CategoryViewAction>() }
    val actionCategoryView: LiveData<CategoryViewAction> get() = _actionCategoryView

    fun getCategory() {
        _actionCategoryView.postValue(CategoryViewAction.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            executeCategory()
        }
    }

    private suspend fun executeCategory() {
        viewModelScope.async(Dispatchers.IO) {
            return@async boRepository.geCategory(::showSuccess, ::showError)
        }.await()
    }

    private fun showError(error: ErrorResponse) {
        _actionCategoryView.postValue(CategoryViewAction.Loading(false))
        _actionCategoryView.postValue(CategoryViewAction.Error(error))
    }

    private fun showSuccess(item: List<CategoryResponse>) {
        _actionCategoryView.postValue(CategoryViewAction.Loading(false))
        _actionCategoryView.postValue(CategoryViewAction.Success(item))
    }
}