package com.example.eldarwallet.ui.balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.databinding.FragmentBalanceBinding
import com.example.eldarwallet.di.Injection

class BalanceFragment : Fragment() {
    private lateinit var binding: FragmentBalanceBinding
    private lateinit var balanceViewModel: BalanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBalanceBinding.inflate(inflater, container, false)

        balanceViewModel =
            ViewModelProvider(
                this,
                BalanceViewModelFactory(Injection.provideGetBalance(requireActivity().applicationContext))
            ).get(BalanceViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()

        balanceViewModel.getBalance("1001")
    }

    private fun observers() {
        balanceViewModel.balance.observe(viewLifecycleOwner) {
            updateBalance(it)
        }
    }

    private fun updateBalance(balance: Long?) {
        binding.balance.text = "$ " + balance.toString()
    }
}