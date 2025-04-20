package com.example.plantapphubx.data

import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceManager(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val KEY_MY_BOOLEAN = "didOnBoardingFinished"
    }

    fun getMyBoolean(): Boolean {
        return sharedPreferences.getBoolean(KEY_MY_BOOLEAN, false)
    }

    fun setMyBoolean(value: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_MY_BOOLEAN, value) }
    }
}
