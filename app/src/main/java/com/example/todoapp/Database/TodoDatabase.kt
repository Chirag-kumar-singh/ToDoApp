package com.example.todoapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.Dao.TodoDao
import com.example.todoapp.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun myTodoDao(): TodoDao

    companion object{
        var INSTANCE: TodoDatabase?=null

        fun getDatabaseInstance(context: Context):TodoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance

            }
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context, TodoDatabase::class.java, "Todo").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}