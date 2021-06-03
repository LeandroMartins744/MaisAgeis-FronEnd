package com.maisageis.ocorrencias.ui.action

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.CategoryResponse

sealed class CategoryViewAction{
    open class Success(val item: List<CategoryResponse>): CategoryViewAction()
    open class Loading(val loading: Boolean): CategoryViewAction()
    open class Error(val item: ErrorResponse): CategoryViewAction()
}