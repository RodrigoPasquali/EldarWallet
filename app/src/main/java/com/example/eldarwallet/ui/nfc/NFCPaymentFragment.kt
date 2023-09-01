package com.example.eldarwallet.ui.nfc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eldarwallet.databinding.FragmentNfcPaymentBinding

class NFCPaymentFragment : Fragment() {
    private lateinit var binding: FragmentNfcPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNfcPaymentBinding.inflate(inflater, container, false)

        return binding.root
    }
}