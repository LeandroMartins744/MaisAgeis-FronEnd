package com.maisageis.ocorrencias.util

import com.google.gson.Gson
import com.maisageis.ocorrencias.model.response.UserResponse

class SecurityData(private val security: Security){

    fun getUer(): UserResponse? {
        val obj = security.getValue(SecurityTypes.User.type)
        return if(obj == "")
            null
        else
            Gson().fromJson(obj, UserResponse::class.java)
    }

    fun setUser(value: UserResponse){
        security.setValue(SecurityTypes.User.type, Gson().toJson(value))
    }

    fun deleteValue(){
        security.deleteValue()
    }
}

enum class SecurityTypes(val type: String){
    User("user")
}