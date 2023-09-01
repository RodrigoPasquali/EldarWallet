package com.example.eldarwallet.ui.nfc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.MyApp
import com.example.eldarwallet.databinding.DialogNfcBinding
import com.example.eldarwallet.di.Injection
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.CardBrand


class NFCFragmentDialog : DialogFragment() {
    private lateinit var binding: DialogNfcBinding
    private lateinit var viewModel: NFCViewModel
    private var cardList: List<Card> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogNfcBinding.inflate(inflater, container, false)

        viewModel =
            ViewModelProvider(
                this,
                NFCViewModelFactory(Injection.provideGetCards(requireActivity().applicationContext))
            ).get(NFCViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingBar(true)
        onGetCardsObserver()
        viewModel.getCardsFromAccount(MyApp.userSession.accountNumber)
        onCloseButtonClick()
    }

    private fun onGetCardsObserver() {
        viewModel.cards.observe(viewLifecycleOwner) { cards ->
            showLoadingBar(false)
            cardList = cards
            setupCardSpinner()
        }
    }

    private fun setupCardSpinner() {
        ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            generateCardDataSpinner(cardList)
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.cardSelector.adapter = adapter
        }
    }

    private fun generateCardDataSpinner(cards: List<Card>): ArrayList<String> {
        val cardsDataSpinner = ArrayList<String>()

        cards.forEach { card ->
            cardsDataSpinner.add(getCardBrand(card.number) + "   N°" + card.number.toString().takeLast(4))
        }

        return cardsDataSpinner
    }

    private fun getCardBrand(cardNumber: Long): String {
        return CardBrand.getBrand(cardNumber).toString().replace("_", " ")
    }

    private fun onCloseButtonClick() {
        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    private fun showLoadingBar(show: Boolean) {
        if (show) {
            binding.loadingBar.visibility = View.VISIBLE
        } else {
            binding.loadingBar.visibility = View.INVISIBLE
        }
    }

    companion object {
        fun newInstance(): NFCFragmentDialog {
            return NFCFragmentDialog()
        }
    }
}