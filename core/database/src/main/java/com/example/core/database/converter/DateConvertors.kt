package com.example.core.database.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Locale

class DateConvertors {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

    @TypeConverter
    fun toTimeMillis(date : String): String {
        return dateFormat.parse(date).time.toString()
    }
}