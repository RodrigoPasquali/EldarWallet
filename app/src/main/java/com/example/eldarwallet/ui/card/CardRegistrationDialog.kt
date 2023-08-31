package com.example.eldarwallet.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.databinding.DialogCardRegistrationBinding
import com.example.eldarwallet.di.Injection
import com.example.eldarwallet.domain.model.Card

class CardRegistrationDialog: DialogFragment() {
    private lateinit var binding: DialogCardRegistrationBinding
    private lateinit var viewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCardRegistrationBinding.inflate(inflater, container, false)

        viewModel =
            ViewModelProvider(
                this,
                CardViewModelFactory(
                    Injection.provideGetCards(requireActivity().applicationContext),
                    Injection.provideSaveCard(requireContext().applicationContext)
                )
            ).get(CardViewModel::class.java)

        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRegisterButton()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun onRegisterButton() {
        binding.registerButton.setOnClickListener {
            viewModel.registerCard(getCardFromData())
            dismiss()
        }
    }

    private fun getCardFromData(): Card {
        return with(binding) {
            Card(
                cardNumber.text.toString().toLong(),
                1001,
                company.text.toString(),
                securityCode.text.toString().toInt(),
                expirationDate.text.toString(),
                name.text.toString(),
                lastname.text.toString()
            )
        }
    }

    companion object {
        fun newInstance(): CardRegistrationDialog {
            return CardRegistrationDialog()
        }
    }
}