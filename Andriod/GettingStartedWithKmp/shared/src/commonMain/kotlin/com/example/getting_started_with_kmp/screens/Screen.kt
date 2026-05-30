package com.example.getting_started_with_kmp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

// Define your screens as sealed class (like enum with state)
sealed class Screen(val title: String) {
    data object Dummy : Screen("Home")
    data object Home : Screen("Home")
    data object Items : Screen("My Items")
    data object Profile : Screen("Profile")

    companion object {
        val screenList = listOf(Home, Items, Profile)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    // Current selected tab
    var selectedTab by remember { mutableStateOf<Screen>(Screen.Dummy) }

    // State for Items screen (shared across tabs if needed)
    var itemsList by remember { mutableStateOf(listOf<String>()) }
    var textInput by remember { mutableStateOf("") }

    // ✅ DEEP LINK SIMULATION
    LaunchedEffect(Unit) {
        // Simulate app opening with "myapp://items" deep link after 1 second
        delay(5000)
        selectedTab = Screen.Items  // Opens Items tab automatically
        // In real app: check actual deep link from system
    }

    // Scaffold provides the screen structure with top bar, bottom bar, etc.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(selectedTab.title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray
                )
            )
        },
        bottomBar = {
            // NavigationBar = Bottom tab bar
            NavigationBar {
                /*NavigationBarItem(
                    icon = {
                        Icon(
                            if (selectedTab == Screen.Home)
                                Icons.Default.Home
                            else
                                Icons.Outlined.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") },
                    selected = selectedTab == Screen.Home,
                    onClick = { selectedTab = Screen.Home }
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            if (selectedTab == Screen.Items)
                                Icons.Default.List
                            else
                                Icons.Outlined.List,
                            contentDescription = "Items"
                        )
                    },
                    label = { Text("Items") },
                    selected = selectedTab == Screen.Items,
                    onClick = { selectedTab = Screen.Items }
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            if (selectedTab == Screen.Profile)
                                Icons.Default.Person
                            else
                                Icons.Outlined.Person,
                            contentDescription = "Profile"
                        )
                    },
                    label = { Text("Profile") },
                    selected = selectedTab == Screen.Profile,
                    onClick = { selectedTab = Screen.Profile }
                )*/

                NavigationBar {
                    Screen.screenList.forEach { screen ->  // Loop through all screens
                        NavigationBarItem(
                            icon = {
                                Text(if (selectedTab == screen) "●" else "○")  // Simple icon
                            },
                            label = { Text(screen.title) },
                            selected = selectedTab == screen,
                            onClick = { selectedTab = screen }
                        )
                    }
                }
            }
        },
        // Optional: Floating action button
        floatingActionButton = {
            if (selectedTab == Screen.Items) {
                FloatingActionButton(
                    onClick = { /* Add quick action */ }
                ) {
                    Icon(Icons.Default.Add, "Add")
                }
            }
        }
    ) { paddingValues ->
        // Content area that changes based on selected tab
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                Screen.Dummy, Screen.Home -> HomeContent()
                Screen.Items -> ItemsContent(
                    itemsList = itemsList,
                    textInput = textInput,
                    onTextChange = { textInput = it },
                    onAddItem = {
                        if (textInput.isNotBlank()) {
                            itemsList = itemsList + textInput
                            textInput = ""
                        }
                    },
                    onDeleteItem = { item -> itemsList = itemsList - item }
                )

                Screen.Profile -> ProfileContent(savedCount = itemsList.size)
            }
        }
    }
}