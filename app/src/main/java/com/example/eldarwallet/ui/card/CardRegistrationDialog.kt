package com.example.eldarwallet.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.MyApp
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.DialogCardRegistrationBinding
import com.example.eldarwallet.di.Injection
import com.example.eldarwallet.domain.model.Card

class CardRegistrationDialog : DialogFragment() {
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRegistrationStatus()
        onRegisterButtonClick()
        onCloseButtonClick()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun observeRegistrationStatus() {
        viewModel.registrationStatus.observe(viewLifecycleOwner) {
            updateStatus(it)
        }
    }

    private fun updateStatus(isValidRegistration: Boolean) {
        if (isValidRegistration) {
            Toast.makeText(
                requireContext(),
                getString(R.string.successful_card_registration),
                Toast.LENGTH_SHORT
            ).show()
            dismiss()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.failed_card_registration),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onRegisterButtonClick() {
        binding.registerButton.setOnClickListener {
            if (!checkForEmptyFields()) {
                viewModel.registerCard(getCardFromData())
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.on_empty_fields),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onCloseButtonClick() {
        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    private fun getCardFromData(): Card {
        return with(binding) {
            Card(
                cardNumber.text.toString().toLong(),
                MyApp.user.accountNumber,
                company.text.toString(),
                securityCode.text.toString().toInt(),
                expirationDate.text.toString(),
                name.text.toString(),
                lastname.text.toString()
            )
        }
    }

    private fun checkForEmptyFields(): Boolean {
        return with(binding) {
            cardNumber.text.isEmpty()
                    || company.text.isEmpty()
                    || securityCode.text.isEmpty()
                    || expirationDate.text.isEmpty()
                    || name.text.isEmpty()
                    ||lastname.text.isEmpty()
        }
    }

    companion object {
        fun newInstance(): CardRegistrationDialog {
            return CardRegistrationDialog()
        }
    }
}