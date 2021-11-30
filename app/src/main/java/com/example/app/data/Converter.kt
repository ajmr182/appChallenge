package com.example.app.data

import androidx.room.TypeConverter

class Converter {
    @TypeConverter

    fun stringToMutable(value: String?): MutableList<String>? {
        return mutableListOf(value?:"")
    }

    @TypeConverter
    fun mutableToString(value: MutableList<String>?): String? {
      return  if (value.toString()=="[]") value.toString() else ""
    }
}