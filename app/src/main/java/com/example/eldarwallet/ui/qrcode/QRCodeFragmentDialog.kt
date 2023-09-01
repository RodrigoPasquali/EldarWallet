package com.example.eldarwallet.ui.qrcode

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.MyApp
import com.example.eldarwallet.databinding.DialogGenerateQrCodeBinding
import com.example.eldarwallet.di.Injection

class QRCodeFragmentDialog : DialogFragment() {
    private lateinit var binding: DialogGenerateQrCodeBinding
    private lateinit var viewModel: QRCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogGenerateQrCodeBinding.inflate(inflater, container, false)

        viewModel =
            ViewModelProvider(
                this,
                QRCodeViewModelFactory(Injection.provideGenerateQR())
            ).get(QRCodeViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeQrImage()
        generateQrCode()
        onCloseButtonClick()
    }

    private fun observeQrImage(){
        viewModel.qrImage.observe(viewLifecycleOwner) {
            onQrCodeGenerateSuccessful(it)
        }
    }

    private fun onQrCodeGenerateSuccessful(qr: Bitmap) {
        binding.qrContainer.setImageBitmap(qr)
        binding.generateCodeTextValue.text = generateValueForQRCode()
    }

    private fun generateQrCode() {
        viewModel.generateQrCode(generateValueForQRCode())
    }

    private fun generateValueForQRCode(): String{
        return MyApp.user.ownerName + MyApp.user.ownerLastname
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun onCloseButtonClick() {
        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(): QRCodeFragmentDialog {
            return QRCodeFragmentDialog()
        }
    }
}