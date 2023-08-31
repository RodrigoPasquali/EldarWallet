package com.example.eldarwallet.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.MyApp
import com.example.eldarwallet.domain.action.GetUser
import com.example.eldarwallet.domain.action.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val login: Login,
    private val getUser: GetUser
) : ViewModel() {
    private var _loginSuccessful = MutableLiveData<Boolean>()
    var loginSuccessful: LiveData<Boolean> = _loginSuccessful

    fun tryLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (loginUser(email, password)) {
                onSuccessfulLogin(email)
            } else {
                onFailureLogin()
            }
        }
    }

    private suspend fun loginUser(email: String, password: String): Boolean {
        return login(email, password) == USER_EXISTS
    }

    private suspend fun onSuccessfulLogin(email: String) {
        _loginSuccessful.postValue(true)
        MyApp.user = getUser(email)
    }

    private fun onFailureLogin() {
        _loginSuccessful.postValue(false)
    }

    private companion object {
        const val USER_EXISTS = 1
    }
}
