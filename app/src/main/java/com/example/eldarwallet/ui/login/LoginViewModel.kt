package com.example.eldarwallet.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.action.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val login: Login
) : ViewModel() {
    private var _loginSuccessful = MutableLiveData<Boolean>()
    var loginSuccessful: LiveData<Boolean> = _loginSuccessful

    fun tryLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (loginUser(email, password)) {
                onSuccessfulLogin()
            } else {
                onFailureLogin()
            }
        }
    }

    private suspend fun loginUser(email: String, password: String): Boolean {
        return login(email, password) == USER_EXISTS
    }

    private fun onSuccessfulLogin() {
        _loginSuccessful.postValue(true)
    }

    private fun onFailureLogin() {
        _loginSuccessful.postValue(false)
    }

    private companion object {
        const val USER_EXISTS = 1
    }
}
