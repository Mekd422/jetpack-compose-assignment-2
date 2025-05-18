package com.example.todoapp.data.local

import androidx.room.*
import com.example.todoapp.data.model.TodoEntity

@Dao

interface TodoDao{
    @Query("SELECT * FROM todo_table")
    suspend fun getAllTodos(): List<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<TodoEntity>)

    @Query("SELECT * FROM todo_table WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoEntity?

    @Query("DELETE FROM todo_table")
    suspend fun clearTodos()
}