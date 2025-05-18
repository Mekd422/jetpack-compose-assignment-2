package com.example.todoapp.repository

import com.example.todoapp.data.local.TodoDao
import com.example.todoapp.data.model.TodoEntity
import com.example.todoapp.data.remote.TodoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.todoapp.data.model.Result

class TodoRepository(
    private val todoDao: TodoDao,
    private val api: TodoApi
) {
    suspend fun fetchTodos(): Result<List<TodoEntity>> {
        return try {
            val todos = api.getTodos()
            val entities = todos.map {
                TodoEntity(it.id, it.userId, it.title, it.completed)
            }
            todoDao.insertTodos(entities)
            Result.Success(entities)
        } catch (e: Exception) {
            val cached = todoDao.getAllTodos()
            if (cached.isNotEmpty()) {
                Result.Success(cached) // fallback to cache
            } else {
                Result.Error("Failed to load data: ${e.localizedMessage}")
            }
        }
    }

    suspend fun getTodoById(id: Int): TodoEntity? = todoDao.getTodoById(id)
}

