package com.example.eldarwallet.ui.card

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.databinding.CardItemBinding
import com.example.eldarwallet.domain.model.Card

class ViewPagerCardAdapter: RecyclerView.Adapter<ViewPagerCardAdapter.CardViewHolder>() {
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
        holder.company.text = card.company
        holder.number.text = card.number.toString()
        holder.expirationDate.text = card.expirationDate
        holder.type.text = card.type
    }

    fun setItem(list: List<Card>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class CardViewHolder(binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val company: TextView = binding.company
        val number: TextView = binding.number
        val expirationDate: TextView = binding.expirationDate
        val type: TextView = binding.type
    }
}