package com.example.eldarwallet.domain.model

enum class CardBrand(string: String) {
    AMERICAN_EXPRESS("AMERICAN EXPRESS"),
    VISA("VISA"),
    MASTERCARD("MASTERCARD"),
    NONE("NONE");

    companion object {
        fun getBrand(cardNumber: Long): CardBrand {
            return when (getFirstDigit(cardNumber)) {
                3 -> AMERICAN_EXPRESS
                4 -> VISA
                5 -> MASTERCARD
                else -> NONE
            }
        }

        private fun getFirstDigit(cardNumber: Long): Int {
            return cardNumber.toString().first().digitToInt()
        }
    }
}
