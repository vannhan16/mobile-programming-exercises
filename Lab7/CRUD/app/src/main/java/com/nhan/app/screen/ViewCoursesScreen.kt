package com.nhan.app.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.nhan.app.model.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewCoursesScreen(navController: NavController) {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    // Biến state để lưu danh sách khóa học lấy về từ Firebase
    val courseList = remember { mutableStateListOf<Course>() }

    // Dùng LaunchedEffect để tự động lấy dữ liệu khi màn hình vừa mở ra
    LaunchedEffect(Unit) {
        db.collection("Courses").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    courseList.clear() // Xóa danh sách cũ
                    for (d in list) {
                        val c: Course? = d.toObject(Course::class.java)
                        if (c != null) {
                            courseList.add(c)
                        }
                    }
                } else {
                    Toast.makeText(context, "Không có dữ liệu", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Lỗi lấy dữ liệu: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách khóa học", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F9D58))
            )
        }
    ) { paddingValues ->
        // Hiển thị danh sách bằng LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(courseList) { course ->
                CourseCard(course)
            }
        }
    }
}

// Thiết kế giao diện cho từng dòng (item) trong danh sách
@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Tên: ${course.courseName}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Thời gian: ${course.courseDuration}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Mô tả: ${course.courseDescription}", fontSize = 14.sp)
        }
    }
}