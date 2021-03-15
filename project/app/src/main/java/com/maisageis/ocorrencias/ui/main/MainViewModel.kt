package com.maisageis.ocorrencias.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maisageis.ocorrencias.model.UserModel
import com.maisageis.ocorrencias.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(private val repository: LoginRepository) : ViewModel() {
    val listUsers = MutableLiveData<List<UserModel>>()

    fun getUsers(){
        CoroutineScope(Dispatchers.Main).launch {
            val user = async {
                repository.LoginUser()
            }.await()

            listUsers.value = user
        }
    }

    class MainViewModelFactory(
            private val repository: LoginRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }

    }
}