package com.maisageis.ocorrencias.repository

import com.maisageis.ocorrencias.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginRepository {
    suspend fun LoginUser(): List<UserModel>{
        return withContext(Dispatchers.Default){
            delay(3000)
            listOf(
                    UserModel("Leandro", "1234"),
                    UserModel("Leandro", "1234")
            )
        }
    }
}