package com.example.app.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.app.model.Tarea

@Dao
interface AgendaDao {

    @Query("SELECT * FROM agenda_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Tarea>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTarea(tarea: Tarea)

    @Update
    fun updateTarea(tarea: Tarea)

    @Delete
    fun deleteTarea(tarea: Tarea)
}