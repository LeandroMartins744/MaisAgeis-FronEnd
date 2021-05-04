package com.maisageis.ocorrencias.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.maisageis.ocorrencias.R

class Security(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.shared_preferences_name), MODE_PRIVATE)

    fun getValue(key: String) = this.preferences.getString(key, "")
    fun setValue(key: String, obj: String) = preferences.edit().putString(key, obj).commit()
}