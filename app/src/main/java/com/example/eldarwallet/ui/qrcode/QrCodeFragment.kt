package com.example.eldarwallet.ui.qrcode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eldarwallet.databinding.FragmentQrCodeBinding

class QrCodeFragment : Fragment() {
    private lateinit var binding: FragmentQrCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQrCodeBinding.inflate(inflater, container, false)

        return binding.root
    }
}