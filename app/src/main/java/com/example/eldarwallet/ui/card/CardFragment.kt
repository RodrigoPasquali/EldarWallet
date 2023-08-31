package com.example.eldarwallet.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.eldarwallet.databinding.FragmentCardBinding
import com.example.eldarwallet.di.Injection
import com.example.eldarwallet.domain.model.Card

class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var viewPagerAdapter: ViewPagerCardAdapter
    private lateinit var cardViewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)

        cardViewModel =
            ViewModelProvider(
                this,
                CardViewModelFactory(
                    Injection.provideGetCards(requireActivity().applicationContext),
                    Injection.provideSaveCard(requireActivity().applicationContext)
                )
            ).get(CardViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()
        cardViewModel.getCardsFromAccount(1000)
        setupAdapter()
        onAddCardButtonClick()
    }

    private fun observers() {
        onGetCardsObserver()
        onRegistrationCardObserver()
    }

    private fun onGetCardsObserver() {
        cardViewModel.cards.observe(viewLifecycleOwner) {
            updateCards(it)
        }
    }

    private fun onRegistrationCardObserver() {
        cardViewModel.registrationStatus.observe(viewLifecycleOwner) {
            cardViewModel.getCardsFromAccount(1000)
        }
    }

    private fun updateCards(cards: List<Card>) {
        viewPagerAdapter.setItem(cards)
    }

    private fun setupAdapter() {
        viewPagerAdapter = ViewPagerCardAdapter()

        with(binding.cardCarrousel) {
            adapter = viewPagerAdapter
            setupCarrousel(this)
        }
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

    private fun onAddCardButtonClick(){
        binding.addCardButton.setOnClickListener {
            CardRegistrationDialog.newInstance().show(childFragmentManager, "Dialog")
        }
    }
}