package com.example.simplecalcpato

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.preference.PreferenceManager


//To use for changing Theme on the whole Application(Declare it on AndroidManifest)

class SimpleCalcApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Get DefaultSharedPreferences
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        //Call SettingsActivity to get the select theme key on next step
        val settingsActivity = SettingsActivity()

        //Update Theme depending on select theme value
        when (sharedPref.getString(settingsActivity.KEY_PREF_SELECT_THEME, "auto")) {

            "night_mode" -> {
                setDefaultNightMode(MODE_NIGHT_YES)
            }

            "day_mode" -> {
                setDefaultNightMode(MODE_NIGHT_NO)
            }

            else -> {
                setDefaultNightMode(MODE_NIGHT_AUTO_BATTERY)
            }
        }



    }
}