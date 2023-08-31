package com.example.eldarwallet.ui.card

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.CardItemBinding
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.CardBrand

class ViewPagerCardAdapter : RecyclerView.Adapter<ViewPagerCardAdapter.CardViewHolder>() {
    var list: List<Card> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = list.get(position)
        with(holder) {
            company.text = card.company
            number.text = card.number.toString()
            expirationDate.text = card.expirationDate
            brand.text = getCardBrand(card.number)
            paintCards(card.number, cardContainer)
        }
    }

    private fun paintCards(number: Long, cardContainer: ConstraintLayout) {
        when (CardBrand.getBrand(number)) {
            CardBrand.AMERICAN_EXPRESS -> cardContainer.setBackgroundColor(
                ContextCompat.getColor(cardContainer.context, R.color.blue)
            )

            CardBrand.MASTERCARD -> cardContainer.setBackgroundColor(
                ContextCompat.getColor(cardContainer.context, R.color.green)
            )

            CardBrand.VISA -> cardContainer.setBackgroundColor(
                ContextCompat.getColor(cardContainer.context, R.color.orange)
            )

            else -> cardContainer.setBackgroundColor(
                ContextCompat.getColor(cardContainer.context, R.color.gray)
            )
        }
    }

    fun setItem(list: List<Card>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    private fun getCardBrand(cardNumber: Long): String {
        return CardBrand.getBrand(cardNumber).toString().replace("_", " ")
    }

    inner class CardViewHolder(binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val company: TextView = binding.company
        val number: TextView = binding.number
        val expirationDate: TextView = binding.expirationDate
        val brand: TextView = binding.brand
        val cardContainer = binding.cardsContainer
    }
}