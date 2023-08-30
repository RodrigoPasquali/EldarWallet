package com.example.eldarwallet.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.eldarwallet.databinding.FragmentCardBinding
import com.example.eldarwallet.domain.model.Card

class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var viewPagerAdapter: ViewPagerCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    private fun setupAdapter() {
        viewPagerAdapter = ViewPagerCardAdapter()

        with(binding.cardCarrousel) {
            adapter = viewPagerAdapter
            setupCarrousel(this)
        }

        viewPagerAdapter.setItem(getDummyCards())
    }

    private fun setupCarrousel(carrousel: ViewPager2) {
        with(carrousel) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }

        val pageMarginPx = 20
        val offsetPx = 30
        carrousel.setPageTransformer { page, position ->
            val viewPager = page.parent.parent as ViewPager2
            val offset = position * -(2 * offsetPx + pageMarginPx)
            if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        }
    }

    private fun getDummyCards() = listOf(
        Card(1000000000000000, "Santander", 123, "01/23", "VISA"),
        Card(2000000000000000, "Galicia", 234, "02/23", "VISA"),
        Card(3000000000000000, "BBVA", 345, "03/23", "MASTERCARD"),
        Card(4000000000000000, "Nacion", 456, "04/23", "MASTERCARD"),
        Card(5000000000000000, "Provincia", 567, "05/23", "AMERICAN EXPRESS")
    )
}