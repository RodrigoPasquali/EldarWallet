package com.example.eldarwallet.ui.qrcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.domain.action.GenerateQR

class QRCodeViewModelFactory(
    private val generateQR: GenerateQR
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QRCodeViewModel(generateQR) as T
    }
}