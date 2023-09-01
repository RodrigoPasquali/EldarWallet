package com.example.eldarwallet

import android.app.Application
import com.example.eldarwallet.domain.model.UserSession

class MyApp : Application() {
    companion object {
        var userSession: UserSession = UserSession( 0,"-", "-")
    }
}