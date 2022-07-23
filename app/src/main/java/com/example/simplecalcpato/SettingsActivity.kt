package com.example.simplecalcpato

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

//Settings Activity includes OnSharedPreferencesOnChangeListener to listen to SharedPrefs changes
class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    //Key value for select theme. It must match name with key on xml file.
    val KEY_PREF_SELECT_THEME= "select_theme"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()

                //Replace settings_activity with SettingFragment (Preference Fragment)
                .replace(R.id.settings, SettingsFragment())
                .commit()

            //Call sharedPreferences and register OnChangeListener
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //The code above uses the typical pattern for adding a fragment to an activity
    // so that the fragment appears as the main content of the activity
    //Setting Fragment which replaces settings_activity
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

        }

    }

    //Fun to updateTheme when Select Theme preference changes
    private fun updateTheme(mode:Int) : Boolean {
        setDefaultNightMode(mode)
        recreate()
        return true

    }

    //On changed function called by the listener change
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        //Check Select Theme Key
        if (key== KEY_PREF_SELECT_THEME )

        //Update Theme depending on select theme value
            when (sharedPreferences?.getString(key, "auto")) {

                "day_mode" -> {
                    updateTheme(MODE_NIGHT_NO)

                }
                "night_mode" -> {
                    updateTheme(MODE_NIGHT_YES)

                }
                else -> {
                    updateTheme(MODE_NIGHT_AUTO_BATTERY)

                }
            }




    }
}