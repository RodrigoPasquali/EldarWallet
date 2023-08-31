package com.example.eldarwallet.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.domain.action.GetUser
import com.example.eldarwallet.domain.action.Login

class LoginViewModelFactory(
    private val login: Login,
    private val getUser: GetUser
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(login, getUser) as T
    }
}