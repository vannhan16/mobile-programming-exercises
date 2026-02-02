package com.example.happybrithday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybrithday.ui.theme.HappyBrithdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBrithdayTheme {
                // Sử dụng Surface với màu trắng rõ rệt để kiểm tra
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.ui.graphics.Color.White
                ) {
                    BirthdayGreetingWithImage(
                        message = "Happy Birthday Bom!",
                        from = "- from Beo"
                    )
                }
            }
        }
    }
}
// Ham 1: chuc nang hien thi hinh anh va sap xep bo cuc
@Composable
fun BirthdayGreetingWithImage(message: String, from: String) {
    val image = painterResource(R.drawable.avatar)
    Box(modifier = Modifier.fillMaxSize()) { // Thêm fillMaxSize vào Box
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize() // Đảm bảo ảnh chiếm hết Box
        )
        BirthdayGreetingWithText(message = message, from = from)
    }
}
@Composable
fun BirthdayGreetingWithText(message: String, from: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // Thêm padding cho cả khung
        verticalArrangement = Arrangement.Center, // Đưa nội dung vào giữa màn hình cho cân đối
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Lời chúc chính với hiệu ứng bóng đổ và màu Gradient (nếu muốn)
        Text(
            text = message,
            fontSize = 45.sp,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFFE91E63), // Màu hồng đậm nổi bật
            fontWeight = FontWeight.Bold,
            style = androidx.compose.ui.text.TextStyle(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color.Black.copy(alpha = 0.3f),
                    offset = androidx.compose.ui.geometry.Offset(4f, 4f),
                    blurRadius = 8f
                )
            )
        )

        // Chữ ký người gửi - làm nghiêng cho nghệ thuật
        Text(
            text = from,
            fontSize = 28.sp,
            color = Color(0xFF3F51B5), // Màu xanh Indigo tương phản
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.End) // Căn lề phải
                .background(
                    color = Color.White.copy(alpha = 0.5f), // Thêm nền mờ cho chữ dễ đọc
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBrithdayTheme {
        BirthdayGreetingWithImage(message = "Happy Birthday Bom!", from = "- from Beo")

    }
}