package com.example.roomdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract  fun dogDao():DogDao

    companion object{

        @Volatile // writes to this field are immediately visible to other threads
        private var instance: AppDatabase? = null  // nullable field

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = instance
            if (tempInstance == null) {
                synchronized(this) {
                    val instanceSync = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                    instance = instanceSync
                    return instanceSync
                }
            } else {
                return tempInstance
            }
        }
    }


}