package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import com.example.todoapp.data.local.TodoDatabase
import com.example.todoapp.data.remote.RetrofitInstance
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.DetailScreen
import com.example.todoapp.ui.MainScreen
import com.example.todoapp.ui.theme.TodoappTheme
import com.example.todoapp.viewmodel.MainViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize DB and Repo
        val db = TodoDatabase.getDatabase(applicationContext)
        val repository = TodoRepository(db.todoDao(), RetrofitInstance.api)

        // Create ViewModel with custom factory
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(repository) as T
            }
        }

        setContent {
            TodoappTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel = viewModel(factory = viewModelFactory)

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(
                            viewModel = viewModel,
                            onTodoClick = { todo ->
                                navController.navigate("detail/${todo.id}")
                            }
                        )
                    }
                    composable(
                        "detail/{todoId}",
                        arguments = listOf(navArgument("todoId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val todoId = backStackEntry.arguments?.getInt("todoId") ?: -1
                        DetailScreen(navController = navController, todoId = todoId,viewModel = viewModel)
                    }
                }
            }

        }
    }
}
