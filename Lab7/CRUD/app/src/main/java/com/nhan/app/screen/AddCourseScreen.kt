package com.nhan.app.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.nhan.app.model.Course // Import model Course bạn vừa tạo ở bước 1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseScreen(navController: NavController) {
    var courseName by remember { mutableStateOf("") }
    var courseDuration by remember { mutableStateOf("") }
    var courseDescription by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GFG", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F9D58))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = courseName,
                onValueChange = { courseName = it },
                label = { Text("Tên khóa học (VD: CS - CNTT)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = courseDuration,
                onValueChange = { courseDuration = it },
                label = { Text("Thời gian học") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = courseDescription,
                onValueChange = { courseDescription = it },
                label = { Text("Mô tả") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
            )

            Button(
                onClick = {
                    if (courseName.isNotEmpty() && courseDuration.isNotEmpty() && courseDescription.isNotEmpty()) {
                        val newCourse = Course(courseName, courseDuration, courseDescription)

                        // Đẩy dữ liệu lên Firebase
                        db.collection("Courses").add(newCourse)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Thêm dữ liệu thành công!", Toast.LENGTH_SHORT).show()
                                courseName = ""
                                courseDuration = ""
                                courseDescription = ""
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp).padding(bottom = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Add Data", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    // Lệnh chuyển sang màn hình View Courses
                    navController.navigate("view_courses")
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("View Courses", fontSize = 16.sp)
            }
        }
    }
}