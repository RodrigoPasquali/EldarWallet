package com.example.eldarwallet.ui.qrcode

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.action.GenerateQR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QRCodeViewModel(
    private val generateQR: GenerateQR
) : ViewModel() {
    private var _qrImage = MutableLiveData<Bitmap>()
    var qrImage: LiveData<Bitmap> = _qrImage


    fun generateQrCode(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generateQR(value)
                if (response.isSuccessful) {
                    val qrImage = BitmapFactory.decodeStream(response.body()?.byteStream())
                    _qrImage.postValue(qrImage)
                } else {
                }
            } catch (e: Exception) {
            }
        }
    }
}