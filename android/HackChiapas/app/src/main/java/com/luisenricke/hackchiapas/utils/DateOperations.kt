package com.luisenricke.hackchiapas.utils

import android.annotation.SuppressLint
import androidx.annotation.Nullable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

@Suppress(
    "unused",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "MemberVisibilityCanBePrivate"
)
@SuppressLint("SimpleDateFormat")
object DateOperations {
    const val PATTERN_DATE = "dd/MM/yyyy"
    const val PATTERN_TIME = "HH:mm:ss"
    const val PATTERN_DATE_TIME = "dd/MM/yyyy HH:mm:ss"

    @Nullable
    fun formatDate(date: Long): String = SimpleDateFormat(PATTERN_DATE).format(Date(date))

    @Nullable
    fun formatTime(date: Long): String = SimpleDateFormat(PATTERN_TIME).format(Date(date))

    @Nullable
    fun formatDateTime(date: Long): String = SimpleDateFormat(PATTERN_DATE_TIME).format(Date(date))

    fun addMinute(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MINUTE, i)
        return cal.time
    }

    fun addHour(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.HOUR_OF_DAY, i)
        return cal.time
    }

    fun addDay(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DAY_OF_YEAR, i)
        return cal.time
    }

    fun addMonth(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MONTH, i)
        return cal.time
    }

    fun addYear(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.YEAR, i)
        return cal.time
    }

    fun subMinute(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MINUTE, i * -1)
        return cal.time
    }

    fun subHour(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.HOUR_OF_DAY, i * -1)
        return cal.time
    }

    fun subDay(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DAY_OF_YEAR, i * -1)
        return cal.time
    }

    fun subMonth(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MONTH, i * -1)
        return cal.time
    }

    fun subYear(date: Date?, i: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.YEAR, i * -1)
        return cal.time
    }

    fun getSecond(date: Date?): Int {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        return cal.get(Calendar.SECOND)
    }

    fun getMinute(date: Date?): Int {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        return cal.get(Calendar.MINUTE)
    }

    fun getHour(date: Date?): Int {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        return cal.get(Calendar.HOUR_OF_DAY)
    }

    fun getDay(date: Date?): Int {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        return cal.get(Calendar.DAY_OF_MONTH)
    }

    fun getMonth(date: Date?): Int {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        return cal.get(Calendar.MONTH)
    }

    fun getYear(date: Date?): Int {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        return cal.get(Calendar.YEAR)
    }

    fun setSecond(date: Date?, second: Int) {
        if (date == null) return
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.SECOND, second)
    }

    fun setMinute(date: Date?, minute: Int) {
        if (date == null) return
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.MINUTE, minute)
    }

    fun setHour(date: Date?, hour: Int) {
        if (date == null) return
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.HOUR_OF_DAY, hour)
    }

    fun setDay(date: Date?, day: Int) {
        if (date == null) return
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.DAY_OF_MONTH, day)
    }

    fun setMonth(date: Date?, month: Int) {
        if (date == null) return
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.MONTH, month - 1)
    }

    fun setYear(date: Date?, year: Int) {
        if (date == null) return
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.YEAR, year)
    }

    /**
     * Returns the given date with the time values cleared.
     */
    fun setStartTime(date: Date?): Date? {
        if (date == null) {
            return null
        }
        val c: Calendar = Calendar.getInstance()
        c.time = date
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)
        return c.time
    }

    /**
     * Returns the given date with time set to the end of the day
     */
    fun setEndTime(date: Date?): Date? {
        if (date == null) {
            return null
        }
        val c: Calendar = Calendar.getInstance()
        c.time = date
        c.set(Calendar.HOUR_OF_DAY, 23)
        c.set(Calendar.MINUTE, 59)
        c.set(Calendar.SECOND, 59)
        c.set(Calendar.MILLISECOND, 999)
        return c.time
    }
}