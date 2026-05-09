package com.nhan.app.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.nhan.app.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()

    // Màu chủ đạo của app Pizza
    val primaryRed = Color(0xFFC62828)
    // Tạo hiệu ứng màu Gradient cho nút Đăng nhập
    val buttonGradient = Brush.horizontalGradient(listOf(Color(0xFFE53935), Color(0xFFB71C1C)))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA)) // Nền trắng ngà cho cảm giác sạch sẽ
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Tiêu đề
        Text(
            text = "Welcome Back",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "PIZZERIA!",
            fontSize = 32.sp,
            color = primaryRed,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Ô nhập Email
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Email") },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email", tint = primaryRed)
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryRed,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = primaryRed
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ô nhập Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password", tint = primaryRed)
            },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryRed,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = primaryRed
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Nút Sign In với hiệu ứng Gradient và Đổ bóng (Shadow)
        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate(Screen.Home.rout)
                            } else {
                                Toast.makeText(context, "Lỗi: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(context, "Vui lòng nhập đủ Email và Password!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .shadow(8.dp, RoundedCornerShape(16.dp)), // Đổ bóng cho nút
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues() // Xóa padding mặc định để Brush phủ kín nút
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(buttonGradient, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Sign In", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Dòng chữ chuyển sang đăng ký (chỉ để làm đẹp giao diện như trong hình Lab)
        val annotatedString = buildAnnotatedString {
            append("Is it first for you? ")
            withStyle(style = SpanStyle(color = primaryRed, fontWeight = FontWeight.Bold)) {
                append("Sign Up now!")
            }
        }
        Text(
            text = annotatedString,
            modifier = Modifier.clickable {
                navController.navigate(Screen.Signup.rout) // Thêm dòng này
            }
        )
    }
}