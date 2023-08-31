package com.example.eldarwallet.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.ActivityLoginBinding
import com.example.eldarwallet.di.Injection
import com.example.eldarwallet.ui.HomeActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel =
            ViewModelProvider(
                this,
                LoginViewModelFactory(
                    Injection.provideLoginUser(applicationContext)
                )
            ).get(LoginViewModel::class.java)

        observeLoginStatus()
        onLoginButtonClick()
    }

    private fun observeLoginStatus() {
        loginViewModel.loginSuccessful.observe(this) {
            updateLoginStatus(it)
        }
    }

    private fun updateLoginStatus(isSuccess: Boolean) {
        if (isSuccess) {
            onSuccessfulLogin()
        } else {
            onFailureLogin()
        }
    }

    private fun onSuccessfulLogin() {
        navigateToHome()
    }

    private fun onFailureLogin() {
        Toast.makeText(this, getString(R.string.invalid_login), Toast.LENGTH_SHORT).show()
    }

    private fun onLoginButtonClick() {
        binding.loginButton.setOnClickListener {
            if (!checkForEmptyFields()) {
                loginViewModel.tryLogin(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
            } else {
                Toast.makeText(this, getString(R.string.complete_empty_fields), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkForEmptyFields(): Boolean {
        return with(binding) {
            password.text!!.isEmpty() || email.text.isEmpty()
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}