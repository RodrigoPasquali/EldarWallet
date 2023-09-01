package com.example.eldarwallet.ui.card

import android.content.Context
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes

class ArrayDateAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val month: Array<String>
) : ArrayAdapter<String>(context, layoutResource, month) {
    override fun getCount(): Int {
        return month.size - 1
    }
}