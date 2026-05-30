package com.example.getting_started_with_kmp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesScreen() {
    // Data for horizontal cards
    val categories = listOf("Electronics", "Clothing", "Books", "Sports", "Toys", "Home", "Beauty", "Food")
    
    // Data for vertical section - each pair of cards has its own list of items
    val sections = listOf(
        SectionData("Popular", listOf("Item 1", "Item 2", "Item 3", "Item 4")),
        SectionData("Recommended", listOf("Product A", "Product B", "Product C")),
        SectionData("Trending", listOf("Trend 1", "Trend 2", "Trend 3", "Trend 4", "Trend 5")),
        SectionData("New Arrivals", listOf("New 1", "New 2", "New 3"))
    )
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Horizontal scrolling section
        item {
            Column {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(categories) { category ->
                        CategoryCard(category = category)
                    }
                }
            }
        }
        
        // Vertical scrolling sections with 2 cards per row
        items(sections) { section ->
            SectionWithTwoCards(
                title = section.title,
                items = section.items
            )
        }
    }
}

// Data class for sections
data class SectionData(val title: String, val items: List<String>)

// Category Card (Horizontal scrolling)
@Composable
fun CategoryCard(category: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(120.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(category, style = MaterialTheme.typography.titleMedium)
        }
    }
}

// Section with 2 cards side by side, each with vertical content
@Composable
fun SectionWithTwoCards(title: String, items: List<String>) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Left Card (with vertical scrolling content)
            CardWithVerticalList(
                title = "Left Side",
                items = items.take(items.size / 2),
                modifier = Modifier.weight(1f)
            )
            
            // Right Card (with vertical scrolling content)
            CardWithVerticalList(
                title = "Right Side",
                items = items.drop(items.size / 2),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// Card that contains vertical scrolling content
@Composable
fun CardWithVerticalList(title: String, items: List<String>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(300.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Card Header
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(12.dp)
            )

            HorizontalDivider()
            
            // Vertical scrolling content inside the card
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(12.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}