package com.example.mapd721_assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mapd721_assignment1.ui.theme.MAPD721_assignment1Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAPD721_assignment1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StudentApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentApp() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val studentNumValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val studentNameValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val courseNameValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val store = StudentStore(context)

    var loadedStudentNum by remember { mutableStateOf("") }
    var loadedStudentName by remember { mutableStateOf("") }
    var loadedCourseName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .clickable { keyboardController?.hide() }
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = studentNumValue.value,
            onValueChange = { studentNumValue.value = it },
            label = { Text("ID") },
        )
        TextField(
            value = studentNameValue.value,
            onValueChange = { studentNameValue.value = it },
            label = { Text("Student Name") }
        )
        TextField(
            value = courseNameValue.value,
            onValueChange = { courseNameValue.value = it },
            label = { Text("Course Name") }
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val (num, name, course) = store.loadStudentData()
                        loadedStudentNum = num
                        loadedStudentName = name
                        loadedCourseName = course
                    }
                },
                modifier = Modifier.size(width = 130.dp, height = 45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
            ) {
                Text("Load", color = MaterialTheme.colorScheme.primary)
            }
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveStudentData(studentNumValue.value.text, studentNameValue.value.text, courseNameValue.value.text)
                    }
                },
                modifier = Modifier.size(width = 130.dp, height = 45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text("Store", color = MaterialTheme.colorScheme.primary)
            }

        }
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    store.clearStudentData()
                    loadedStudentNum = ""
                    loadedStudentName = ""
                    loadedCourseName = ""
                }
            },
            modifier = Modifier.size(width = 276.dp, height = 45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
        ) {
            Text("Reset", color = MaterialTheme.colorScheme.primary)
        }
        Text(text = "Loaded ID: $loadedStudentNum")
        Text(text = "Loaded Student Name: $loadedStudentName")
        Text(text = "Loaded Course Name: $loadedCourseName")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Student Name: Yu-Ling Wu")
        Text("Student ID: 301434538")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MAPD721_assignment1Theme {
        StudentApp()
    }
}