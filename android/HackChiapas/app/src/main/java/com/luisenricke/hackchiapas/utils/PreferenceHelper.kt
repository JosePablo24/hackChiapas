package com.luisenricke.hackchiapas.utils

import android.content.Context
import androidx.preference.PreferenceManager
import com.luisenricke.hackchiapas.common.extension.getClass
import kotlin.reflect.KClass

@Suppress("unused", "UNCHECKED_CAST")
object PreferenceHelper {

    @Throws(IllegalArgumentException::class)
    fun set(context: Context, variable: String, value: Any) {
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        when (value.getClass()) {
            String::class -> editor.putString(variable, value.toString()).apply()
            Int::class -> editor.putInt(variable, value.toString().toInt()).apply()
            Long::class -> editor.putLong(variable, value.toString().toLong()).apply()
            Float::class -> editor.putFloat(variable, value.toString().toFloat()).apply()
            Boolean::class -> editor.putBoolean(variable, value.toString().toBoolean()).apply()
            else -> throw IllegalArgumentException("The type ${value.getClass()} isn't support it to SharedPreferences")
        }
    }

    @Throws(IllegalArgumentException::class)
    fun <T : Any> get(context: Context, variable: String, type: KClass<T>): T? {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (!sharedPreferences.all.containsKey(variable)) return null
        return when (type) {
            String::class -> sharedPreferences.getString(variable, "") as T
            Int::class -> sharedPreferences.getInt(variable, -1) as T
            Long::class -> sharedPreferences.getLong(variable, -1L) as T
            Float::class -> sharedPreferences.getFloat(variable, -1F) as T
            Boolean::class -> sharedPreferences.getBoolean(variable, false) as T
            else -> throw IllegalArgumentException("The type $type isn't support it to SharedPreferences")
        }
    }

    fun delete(context: Context, variable: String): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (!sharedPreferences.all.containsKey(variable)) return false
        return sharedPreferences.edit().remove(variable).commit()
    }
}