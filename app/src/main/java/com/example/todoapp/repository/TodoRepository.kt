package com.example.todoapp.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.Dao.TodoDao
import com.example.todoapp.model.Todo

class TodoRepository(val dao: TodoDao) {

    fun getAllTodo():LiveData<List<Todo>>{
        return dao.getTodo()
    }

    fun getHighTodo():LiveData<List<Todo>> = dao.getHighTodo()

    fun getMediumTodo():LiveData<List<Todo>> = dao.getMediumTodo()

    fun getLowTodo():LiveData<List<Todo>> = dao.getLowTodo()

    fun insertTodo(todo: Todo){
        dao.InsertTodo(todo)
    }

    fun deleteTodo(id: Int){
        dao.deleteTodo(id)
    }

    fun updateTodo(todo: Todo){
        dao.updateTodo(todo)
    }
}