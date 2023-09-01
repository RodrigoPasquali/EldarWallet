package com.example.eldarwallet

import android.app.Application
import com.example.eldarwallet.domain.model.User

class MyApp : Application() {
    companion object {
        var user: User = User("","", 0,"-", "-")
    }
}