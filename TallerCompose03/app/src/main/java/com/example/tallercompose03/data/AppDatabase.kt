package com.example.tallercompose03.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CiudadEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ciudadDao(): CiudadDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "weather_db",
                ).build().also { INSTANCE = it }
            }
        }
    }
}
