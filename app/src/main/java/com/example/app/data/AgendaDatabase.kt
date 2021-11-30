package com.example.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app.model.Tarea

@Database(entities = [Tarea::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AgendaDatabase:RoomDatabase() {

    abstract fun agendaDao():AgendaDao

    companion object{

        @Volatile
        private var INSTANCE: AgendaDatabase? = null

        fun getDatabase(context: Context): AgendaDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {

                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AgendaDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}