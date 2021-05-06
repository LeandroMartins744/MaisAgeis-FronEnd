package com.maisageis.ocorrencias.repository

import com.maisageis.ocorrencias.data.util.ApiHelper
import com.maisageis.ocorrencias.model.UserModel
import com.maisageis.ocorrencias.model.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class LoginRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers(): Result<java.lang.Exception, UserResponse> {
        return try {
            Result.Success(apiHelper.getUsers("Leandro", "1234").body()!!)
        }
        catch (ex: Exception){
            Result.Error(ex)
        }
    }

}

class LoginxxxxxRepository {
    suspend fun LoginUser(): Result<String, UserModel>{// List<UserModel>{
        /*return withContext(Dispatchers.Default){
            delay(3000)
            listOf(
                    UserModel("Leandro", "1234"),
                    UserModel("Leandro", "1234")
            )
        }

         */
        return Result.Success(UserModel("Leandro", "1234"))

    }
}


sealed class Result<out R, out T> {
    data class Success<out T>(val dataSuccess: T) : Result<Nothing, T>()
    data class Error<out R>(val dataError: R) : Result<R, Nothing>()

    fun fold(onError: (R) -> Any, onSuccess: (T) -> Any): Any =
        when(this){
            is Error -> onError(dataError)
            is Success -> onSuccess(dataSuccess)
        }
}