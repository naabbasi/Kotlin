package com.example.getting_started_with_kmp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

// Define your screens as sealed class (like enum with state)
sealed class Screen(val title: String) {
    object Home : Screen("Home")
    object Items : Screen("My Items")
    object Profile : Screen("Profile")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    // Current selected tab
    var selectedTab by remember { mutableStateOf<Screen>(Screen.Home) }
    
    // State for Items screen (shared across tabs if needed)
    var itemsList by remember { mutableStateOf(listOf<String>()) }
    var textInput by remember { mutableStateOf("") }
    
    // Scaffold provides the screen structure with top bar, bottom bar, etc.
    Scaffold(
        topBar = { 
            TopAppBar(
                title = { Text(selectedTab.title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            // NavigationBar = Bottom tab bar
            NavigationBar {
                NavigationBarItem(
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
                )
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
                Screen.Home -> HomeContent()
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
                Screen.Profile -> ProfileContent()
            }
        }
    }
}