package com.example.eldarwallet.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eldarwallet.databinding.ActivityLoginBinding
import com.example.eldarwallet.ui.HomeActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onLoginButtonClick()
    }

    private fun onLoginButtonClick() {
        binding.loginButton.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}