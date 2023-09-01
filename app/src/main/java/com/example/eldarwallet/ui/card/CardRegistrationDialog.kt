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
        setupExpirationDate()
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

    private fun setupExpirationDate() {
        setupMonthSpinner()
        setupYearSpinner()
    }

    private fun setupMonthSpinner() {
        val months = resources.getStringArray(R.array.month_items)

        activity?.applicationContext?.let {
            ArrayDateAdapter(
                it,
                android.R.layout.simple_spinner_item,
                months,
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.monthExpirationDate.adapter = adapter
                binding.monthExpirationDate.setSelection(months.size - 1)
            }
        }
    }

    private fun setupYearSpinner() {
        val years = resources.getStringArray(R.array.years_items)

        activity?.applicationContext?.let {
            ArrayDateAdapter(
                it,
                android.R.layout.simple_spinner_item,
                years,
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.yearExpirationDate.adapter = adapter
                binding.yearExpirationDate.setSelection(years.size - 1)
            }
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
                MyApp.userSession.accountNumber,
                company.text.toString(),
                securityCode.text.toString().toInt(),
                getExpirationDate(),
                name.text.toString(),
                lastname.text.toString()
            )
        }
    }

    private fun getExpirationDate(): String {
        return binding.monthExpirationDate.selectedItem.toString() + "/" + binding.yearExpirationDate.selectedItem.toString()
    }

    private fun checkForEmptyFields(): Boolean {
        return with(binding) {
            cardNumber.text.isEmpty()
                    || company.text.isEmpty()
                    || securityCode.text.isEmpty()
                    || name.text.isEmpty()
                    || lastname.text.isEmpty()
                    || (monthExpirationDate.selectedItem.toString() == "Mes")
                    || (yearExpirationDate.selectedItem.toString() == "Año")
        }
    }

    companion object {
        fun newInstance(): CardRegistrationDialog {
            return CardRegistrationDialog()
        }
    }
}