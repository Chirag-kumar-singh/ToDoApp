package com.example.todoapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todoapp.Database.TodoDatabase
import com.example.todoapp.model.Todo
import com.example.todoapp.repository.TodoRepository

class TodoViewModel(application: Application): AndroidViewModel(application) {

    val repository: TodoRepository

    init {
        val dao = TodoDatabase.getDatabaseInstance(application).myTodoDao()
        repository=TodoRepository(dao)
    }

    fun addTodo(todo: Todo){
        repository.insertTodo(todo)
    }

    fun getTodo(): LiveData<List<Todo>> = repository.getAllTodo()

    fun deleteTodo(id: Int){
        repository.deleteTodo(id)
    }

    fun updateTodo(todo: Todo){
        repository.updateTodo(todo)
    }
}