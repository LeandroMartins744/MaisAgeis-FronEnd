package com.maisageis.ocorrencias.ui.action

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.CoodResponse

sealed class DetailsViewAction{
    open class Success(val item: CoodResponse): DetailsViewAction()
    open class Loading(val loading: Boolean): DetailsViewAction()
    open class Error(val item: ErrorResponse): DetailsViewAction()
}