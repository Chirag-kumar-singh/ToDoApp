package com.example.todoapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getTodo():LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertTodo(todo: Todo)

    @Query("DELETE FROM Todo WHERE id=:id")
    fun deleteTodo(id: Int)

    @Update
    fun updateTodo(todo: Todo)
}