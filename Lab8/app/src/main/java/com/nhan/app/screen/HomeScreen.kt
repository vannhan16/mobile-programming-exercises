package com.nhan.app.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.nhan.app.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val primaryRed = Color(0xFFC62828)
    val goldYellow = Color(0xFFFFB300)

    // Lấy email người dùng hiện tại để hiển thị lời chào
    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: "Guest"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("PIZZERIA", color = Color.White, fontWeight = FontWeight.Black)
                },
                actions = {
                    IconButton(onClick = {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate(Screen.Signin.rout) {
                            popUpTo(Screen.Home.rout) { inclusive = true }
                        }
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryRed)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFFF8E1)), // Nền vàng kem nhẹ nhàng
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Phần Banner Hình ảnh (Bạn có thể thêm hình pizza vào thư mục res/drawable)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                    .background(primaryRed)
            ) {
                // Thay 'R.drawable.pizza_banner' bằng hình của bạn hoặc dùng Box màu tạm thời
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🍕",
                        fontSize = 80.sp
                    )
                    Text(
                        text = "Hôm nay bạn muốn ăn gì?",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Lời chào người dùng
            Text(
                text = "Chào mừng quay trở lại,",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = userEmail,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = primaryRed
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Thông điệp quảng cáo (giống trong file Lab 8)
            Text(
                text = "Delivering\nDeliciousness right\nto your door!",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp,
                color = Color(0xFF3E2723)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Nút Bắt đầu đặt hàng (Start Order)
            Button(
                onClick = { /* Xử lý đặt hàng */ },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryRed),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("START ORDER", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Nút đăng xuất phụ ở phía dưới
            TextButton(
                onClick = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate(Screen.Signin.rout) {
                        popUpTo(Screen.Home.rout) { inclusive = true }
                    }
                },
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text("Sign Out", color = primaryRed, fontWeight = FontWeight.Bold)
            }
        }
    }
}