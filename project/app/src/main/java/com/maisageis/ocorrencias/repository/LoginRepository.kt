package com.maisageis.ocorrencias.repository

import com.maisageis.ocorrencias.data.util.ApiHelper
import com.maisageis.ocorrencias.data.util.RetrofitInitializer
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.UserModel
import com.maisageis.ocorrencias.model.response.UserResponse
import java.lang.reflect.Executable

class LoginRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers(login: String, pass: String, onSuccess: (UserResponse) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            apiHelper.getUsers(login, pass, onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }

    suspend fun getUser2(login: String, pass: String){
            apiHelper.getUsers2(login, pass).let { return@let it}
              //  return it
            //}
    }

    suspend fun getUser3(login: String, pass: String) = RetrofitInitializer.getRetrofit().getLoginNew2(login, pass)
    suspend fun getUserTest(login: String, pass: String): Result<java.lang.Exception, UserResponse>{
        apiHelper.getUsers2("teste@teste.com", "123456").let {
            var teste = it
            return Result.Success(UserResponse(1, "", "", "", "", true))
        }
    }
}

sealed class Result<out L, out R> {
    data class Success<out R>(val dataSuccess: R) : Result<Nothing, R>()
    data class Error<out L>(val dataError: L) : Result<L, Nothing>()

    fun fold(onError: (L) -> Any, onSuccess: (R) -> Any): Any =
        when(this){
            is Error -> onError(dataError)
            is Success -> onSuccess(dataSuccess)
        }

    val isSuccess get() = this is Success<R>
    val isError get() = this is Error<L>

    fun <L> makeError(data: L) = Success(data)
    fun <R> makeSuccess(data: R) = Success(data)
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {f(this(it))}

fun<T, L, R> Result<L, R>.flatMap(fn: (R) -> Result<L, T>): Result<L, T> =
    when(this){
        is Result.Error -> Result.Error(dataError)
        is Result.Success -> fn(dataSuccess)
    }

fun<T, L, R> Result<L, R>.map(fn: (R) -> (T)): Result<L, T> = this.flatMap(fn.c(::makeSuccess))