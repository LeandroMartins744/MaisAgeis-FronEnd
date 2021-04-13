package com.maisageis.ocorrencias.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maisageis.ocorrencias.data.util.ReturnData
import com.maisageis.ocorrencias.data.utilutil.LoginData
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.UserModel
import com.maisageis.ocorrencias.model.response.UserResponse

class LoginViewModel:ViewModel(), ReturnData{
    val user = MutableLiveData<UserResponse>()
    val erro = MutableLiveData<ErrorResponse>()
    val loadPage = MutableLiveData<Boolean>().apply { value = false }

    var loginData: LoginData = LoginData()

    fun loadData(login: String, pass: String){
        this.Loading()
        loginData.Login(login, pass, this)
    }

    private fun setLoadPage(value: Boolean){
        this.loadPage.value = value
    }

    override fun <UserResponse> Success(result: UserResponse) {
        user.value = result as com.maisageis.ocorrencias.model.response.UserResponse
    }

    override fun Error(error: ErrorResponse) {
        this.setLoadPage(false)
        erro.value = error
    }

    override fun Loading() {
        this.setLoadPage(true)
    }
}