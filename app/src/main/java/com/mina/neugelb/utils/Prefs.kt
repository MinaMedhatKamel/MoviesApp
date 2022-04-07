package com.mina.neugelb.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mina.neugelb.data.model.ConfigData

class Prefs(context: Context) {

    val PREFS_FILENAME = "com.mina.neugelb"
    val CONFIG_PREFS = "config_prefs"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);
    val gson = Gson()

    var config: ConfigData?
        get() = setConfigDataPref()
        set(value) = setConfigDataPref(value)


    private fun setConfigDataPref(value: ConfigData?) {
        if (value != null) {
            val json = gson.toJson(value)
            prefs.edit().putString(CONFIG_PREFS, json).apply()
        } else {
            prefs.edit().remove(CONFIG_PREFS).apply()
        }
    }

    private fun setConfigDataPref(): ConfigData? {
        val json = prefs.getString(CONFIG_PREFS, "")
        val userPref = gson.fromJson<ConfigData>(json, ConfigData::class.java)
        return userPref
    }
}