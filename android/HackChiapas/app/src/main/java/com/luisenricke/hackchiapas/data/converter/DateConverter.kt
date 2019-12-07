package com.luisenricke.hackchiapas.data.converter

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.util.Date

@SuppressLint("SimpleDateFormat")
object DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}
