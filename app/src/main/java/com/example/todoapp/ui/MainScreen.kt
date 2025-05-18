package com.example.todoapp.ui

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.viewmodel.MainViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.clickable
import androidx.compose.material3.SearchBarDefaults.colors
import com.example.todoapp.data.model.Todo
import com.example.todoapp.data.model.TodoEntity
import com.example.todoapp.data.model.Result
import androidx.compose.ui.Alignment



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, onTodoClick: (TodoEntity) -> Unit) {
    val todoState by viewModel.todoState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTodos()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(title = { Text("Todo App", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
            )

        }
    ) { padding ->

        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background)) {

            when (val state = todoState) {
                is Result.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is Result.Success -> {
                    LazyColumn {
                        items(state.data) { todo ->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .clickable { onTodoClick(todo) },
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                                ),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(todo.title, style = MaterialTheme.typography.titleMedium,color = MaterialTheme.colorScheme.onSecondaryContainer)
                                    Text(if (todo.completed) "Completed" else "Incomplete", color = MaterialTheme.colorScheme.onSecondaryContainer)
                                }
                            }
                        }
                    }
                }
                is Result.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(state.message, color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadTodos() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

