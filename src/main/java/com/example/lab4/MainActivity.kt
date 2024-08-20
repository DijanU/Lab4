package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.lab4.ui.theme.Lab4Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
                App()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab4Theme {
        Greeting("Android")
    }
}

@Composable
fun CustomItem(name: String, imageUrl: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = "Imagen de la receta",
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )
        Text(text = name, color = Color.Black, style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun App() {
    val itemList = remember { mutableStateListOf<Pair<String, String>>() }
    val inputText = remember { mutableStateOf("") }
    val imageUrl = remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TextField(
                value = inputText.value,
                onValueChange = { inputText.value = it },
                label = { Text("Ingresa el nombre de la receta:") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = imageUrl.value,
                onValueChange = { imageUrl.value = it },
                label = { Text("Ingresa el URL de la imagen:") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                if (inputText.value.isNotBlank() && imageUrl.value.isNotBlank()) {
                    itemList.add(inputText.value to imageUrl.value)
                    inputText.value = ""
                    imageUrl.value = ""
                }
            }) {
                Text(text = "Agregar")
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(itemList) { item ->
                    CustomItem(name = item.first, imageUrl = item.second)
                }
            }
        }
    }
}