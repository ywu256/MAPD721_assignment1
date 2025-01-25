package com.example.mapd721_assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mapd721_assignment1.ui.theme.MAPD721_assignment1Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAPD721_assignment1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    StudentApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentApp() {
    var id by remember { mutableStateOf("538") }
    var username by remember { mutableStateOf("") }
    var courseName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID") },
            readOnly = true
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        TextField(
            value = courseName,
            onValueChange = { courseName = it },
            label = { Text("Course Name") }
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {  },
                modifier = Modifier.size(width = 130.dp, height = 45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
            ) {
                Text("Load", color = MaterialTheme.colorScheme.primary)
            }
            Button(
                onClick = {  },
                modifier = Modifier.size(width = 130.dp, height = 45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text("Store", color = MaterialTheme.colorScheme.primary)
            }

        }
        Button(
            onClick = {  },
            modifier = Modifier.size(width = 276.dp, height = 45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
        ) {
            Text("Reset", color = MaterialTheme.colorScheme.primary)
        }
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