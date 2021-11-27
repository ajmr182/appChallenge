package com.example.app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.app.data.AgendaDatabase
import com.example.app.model.Tarea
import com.example.app.repository.AgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgendaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository:AgendaRepository
    val readAllData: LiveData<List<Tarea>>

    init {
        val agendaDao=AgendaDatabase.getDatabase(application).agendaDao()
        repository= AgendaRepository(agendaDao)
        readAllData = repository.readAllData
    }

    fun addTarea(tarea: Tarea) {

        viewModelScope.launch(Dispatchers.IO) {

            repository.addTarea(tarea)
        }
    }

    fun updateTarea(tarea: Tarea) {

        viewModelScope.launch(Dispatchers.IO) {

            repository.updateTarea(tarea)
        }
    }

    fun deleteTarea(tarea: Tarea) {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteTarea(tarea)
        }
    }
}