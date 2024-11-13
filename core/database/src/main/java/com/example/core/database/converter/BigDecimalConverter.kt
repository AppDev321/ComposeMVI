package com.example.core.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalConverter {

    @TypeConverter
    fun convertToString(value: BigDecimal): String {
        return value.toString()
    }

    @TypeConverter
    fun convertToBigDecimal(value: String): BigDecimal {
        return value.toBigDecimal()
    }

}