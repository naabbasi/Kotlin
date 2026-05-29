package com.example.getting_started_with_kmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import gettingstartedwithkmp.shared.generated.resources.Res
import gettingstartedwithkmp.shared.generated.resources.compose_multiplatform

enum class Screen {
    Home, Grid
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf(Screen.Home) }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentScreen == Screen.Home,
                        onClick = { currentScreen = Screen.Home },
                        icon = { 
                            Box(Modifier.size(24.dp).background(if (currentScreen == Screen.Home) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, shape = MaterialTheme.shapes.small))
                        },
                        label = { Text("Home") }
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.Grid,
                        onClick = { currentScreen = Screen.Grid },
                        icon = { 
                            Box(Modifier.size(24.dp).background(if (currentScreen == Screen.Grid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, shape = MaterialTheme.shapes.small))
                        },
                        label = { Text("Grid") }
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                when (currentScreen) {
                    Screen.Home -> HomeContent()
                    Screen.Grid -> GridContent()
                }
            }
        }
    }
}

@Composable
fun HomeContent() {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}

@Composable
fun GridContent() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Item $index", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
