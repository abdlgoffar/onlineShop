package com.example.onlineshop

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager<T>(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
   //method save pref
    fun add(key: String, value: T) {
        with(sharedPreferences.edit()) {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> error("Unsupported data type")
            }
            apply()
        }
    }
   //method get pref
    fun get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue as String) as T
            is Int -> sharedPreferences.getInt(key, defaultValue as Int) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue as Float) as T
            is Long -> sharedPreferences.getLong(key, defaultValue as Long) as T
            else -> error("Unsupported data type")
        }
    }
    // Method untuk menghapus data dari SharedPreferences
    fun remove(key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            apply()
        }
    }
}
