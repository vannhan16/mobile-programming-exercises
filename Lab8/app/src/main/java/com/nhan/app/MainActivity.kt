package com.nhan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nhan.app.navigation.Screen
import com.nhan.app.screen.HomeScreen
import com.nhan.app.screen.SignInScreen
import com.nhan.app.screen.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mynavigation()
        }
    }
}

@Composable
fun Mynavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Signin.rout
    ) {
        composable(Screen.Signin.rout) {
            SignInScreen(navController = navController)
        }
        composable(Screen.Home.rout) {
            HomeScreen(navController = navController)
        }
        // Thêm màn hình Đăng ký vào đây
        composable(Screen.Signup.rout) {
            SignUpScreen(navController = navController)
        }
    }
}