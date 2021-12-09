package com.example.app.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Parcelize
@Entity(tableName = "agenda_table")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val nombreTarea:String,
    val contenidoTarea:String,
    val fechaFinal:String,
    val fechaInicio:String,
    var tareaEstaLista:Boolean,
    var comentarios:ArrayList<String>
) : Parcelable
