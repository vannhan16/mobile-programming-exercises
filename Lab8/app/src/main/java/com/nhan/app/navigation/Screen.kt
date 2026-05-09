package com.nhan.app.navigation

sealed class Screen(val rout: String) {
    object Home : Screen(rout = "home")
    object Signin : Screen(rout = "signin")
    object Signup : Screen(rout = "signup")
}