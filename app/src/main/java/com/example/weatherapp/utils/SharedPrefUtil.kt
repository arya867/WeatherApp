package com.example.weatherapp.utils

import android.content.Context
import com.example.weatherapp.data.models.FavoriteLocation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPrefUtil {
    private const val PREFS_NAME = "weather_app_prefs"
    private const val FAVORITES_KEY = "favorites"

    fun getFavorites(context: Context): List<FavoriteLocation> {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(FAVORITES_KEY, "[]")
        return try {
            val type = object : TypeToken<List<FavoriteLocation>>() {}.type
            Gson().fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList() // Kembalikan daftar kosong jika parsing gagal
        }
    }

    fun saveFavorites(context: Context, favorites: List<FavoriteLocation>) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(favorites)
        editor.putString(FAVORITES_KEY, json)
        editor.apply()
    }
}
