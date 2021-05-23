package com.maisageis.ocorrencias.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maisageis.ocorrencias.data.GetLocationData
import com.maisageis.ocorrencias.data.util.ReturnData
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.DataResult

class MainViewModel(): ViewModel(), ReturnData {
    val data = MutableLiveData<DataResult>()
    val erro = MutableLiveData<ErrorResponse>()
    val loadPage = MutableLiveData<Boolean>().apply { value = false }

    var loginData: GetLocationData = GetLocationData()

    fun loadData(latitude: String, longetitude: String){
        this.Loading()
        loginData.Location(latitude, longetitude, this)
    }

    private fun setLoadPage(value: Boolean){
        this.loadPage.value = value
    }

    override fun <UserResponse> Success(result: UserResponse) {
        data.value = result as com.maisageis.ocorrencias.model.response.DataResult
    }

    override fun Error(error: ErrorResponse) {
        this.setLoadPage(false)
        erro.value = error
    }

    override fun Loading() {
        this.setLoadPage(true)
    }
}