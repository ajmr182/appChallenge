package com.example.app.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "agenda_table")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val nombreTarea:String,
    val contenidoTarea:String,
    val finalizaTarea:String
) : Parcelable
