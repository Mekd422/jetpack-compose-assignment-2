package com.example.todoapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.TodoEntity
import com.example.todoapp.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import com.example.todoapp.data.model.Result

class MainViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _todoState = MutableStateFlow<Result<List<TodoEntity>>>(Result.Loading)
    val todoState: StateFlow<Result<List<TodoEntity>>> = _todoState

    fun loadTodos() {
        viewModelScope.launch {
            _todoState.value = Result.Loading
            val result = repository.fetchTodos()
            _todoState.value = result
        }
    }

    fun getTodoById(id: Int): Flow<TodoEntity?> = flow {
        emit(repository.getTodoById(id))
    }.flowOn(Dispatchers.IO)
}

