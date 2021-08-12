package com.newcompany.andesofttest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newcompany.andesofttest.viewmodel.MainActivityViewModel
import com.newcompany.roomdatabasetask.repository.UserRepository
import java.lang.IllegalArgumentException

class UserViewModelFactory(
        private val repository: UserRepository
        ):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
         return MainActivityViewModel(repository) as T
     }
        throw IllegalArgumentException("Unknown View Model class")
    }

}