package com.maisageis.ocorrencias.ui.action

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.StreetCepResponse

sealed class SearchCepViewAction{
    open class Success(val item: StreetCepResponse): SearchCepViewAction()
    open class Loading(val loading: Boolean): SearchCepViewAction()
    open class Error(val item: ErrorResponse): SearchCepViewAction()
}