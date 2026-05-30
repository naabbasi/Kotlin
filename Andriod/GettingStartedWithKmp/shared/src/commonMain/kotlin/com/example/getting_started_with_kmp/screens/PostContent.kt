package com.example.getting_started_with_kmp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.getting_started_with_kmp.models.Post
import com.example.getting_started_with_kmp.network.service.PostService

@Composable
fun PostsContent() {
    val scope = rememberCoroutineScope()
    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    val postService = remember { PostService() }
    
    // Load posts when screen appears
    LaunchedEffect(Unit) {
        isLoading = true
        errorMessage = null
        try {
            posts = postService.fetchAllPosts()
            //val apiResponse = postService.fetchAllPostsSafe()
        } catch (e: Exception) {
            errorMessage = e.message
        }
        isLoading = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        when {
            isLoading -> Text("Loading posts...")
            errorMessage != null -> {
                Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                Button(onClick = { /* retry logic */ }) {
                    Text("Retry")
                }
            }
            posts.isEmpty() -> Text("No posts found")
            else -> LazyColumn {
                items(posts.take(10)) { post ->
                    Card(modifier = Modifier.padding(8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = post.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = post.body,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}