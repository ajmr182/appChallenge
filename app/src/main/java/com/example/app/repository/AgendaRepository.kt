package com.example.app.repository

import androidx.lifecycle.LiveData
import com.example.app.data.AgendaDao
import com.example.app.model.Tarea

class AgendaRepository (private val agendaDao: AgendaDao) {

    val readAllData: LiveData<List<Tarea>> = agendaDao.readAllData()

    fun addTarea(tarea: Tarea){

        agendaDao.addTarea(tarea)
    }
    fun updateTarea(tarea: Tarea){

        agendaDao.updateTarea(tarea)
    }
    fun deleteTarea(tarea: Tarea){

        agendaDao.deleteTarea(tarea)
    }
}

