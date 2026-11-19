package com.example.appcine.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FilmeFavorito::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmeFavoritoDao(): FilmeFavoritoDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "appcine_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}