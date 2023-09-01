package com.example.eldarwallet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eldarwallet.databinding.ActivityHomeBinding
import com.example.eldarwallet.ui.qrcode.QRCodeFragmentDialog

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onQRCodeSectionClick()
    }

    private fun onQRCodeSectionClick(){
        binding.qrCodeContainer.setOnClickListener {
            QRCodeFragmentDialog.newInstance().show(supportFragmentManager, "QRCodeDialog")
        }
    }
}