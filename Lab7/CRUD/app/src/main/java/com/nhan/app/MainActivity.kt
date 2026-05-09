package com.nhan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nhan.app.screen.AddCourseScreen
import com.nhan.app.screen.ViewCoursesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Gọi hàm giao diện Compose vào đây
            AppNavigation()
        }
    }
}
@Composable
fun AppNavigation() {
    // Khởi tạo bộ điều khiển
    val navController = rememberNavController()

    // Cấu hình các route (đường dẫn)
    NavHost(navController = navController, startDestination = "add_course") {
        // Màn hình 1: Thêm khóa học
        composable("add_course") {
            AddCourseScreen(navController = navController)
        }

        // Màn hình 2: Xem khóa học
        composable("view_courses") {
            ViewCoursesScreen(navController = navController)
        }
    }
}