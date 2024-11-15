package com.example.common



import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceDelegate<T>(
    private val context: Context,
    private val key: String,
    private val defaultValue: T?
) : ReadWriteProperty<Any, T?> {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return when (defaultValue) {
            is String? -> sharedPreferences.getString(key, defaultValue) as T?
            is Int? -> sharedPreferences.getInt(key, defaultValue ?: 0) as T?
            is Boolean? -> sharedPreferences.getBoolean(key, defaultValue ?: false) as T?
            is Long? -> sharedPreferences.getLong(key, defaultValue ?: 0L) as T?
            is Float? -> sharedPreferences.getFloat(key, defaultValue ?: 0f) as T?
            else -> throw IllegalArgumentException("This type can't be saved into SharedPreferences")
        }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        with(sharedPreferences.edit()) {
            when (value) {
                is String? -> putString(key, value)
                is Int? -> putInt(key, value ?: 0)
                is Boolean? -> putBoolean(key, value ?: false)
                is Long? -> putLong(key, value ?: 0L)
                is Float? -> putFloat(key, value ?: 0f)
                else -> throw IllegalArgumentException("This type can't be saved into SharedPreferences")
            }
            apply()
        }
    }
}
