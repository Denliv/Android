package com.preference

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.ListPreference
import android.preference.PreferenceActivity
import android.preference.PreferenceManager
import android.preference.SwitchPreference
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi


class CustomPreferenceActivity : PreferenceActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @Deprecated("Deprecated in Java")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
        updateListPrefSummary()
        val editText: EditText? =
            (findPreference(getString(R.string.text_size)) as EditTextPreference).editText
        if (editText != null) {
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
        val themeSwitch: SwitchPreference =
            (findPreference(getString(R.string.switch_theme)) as SwitchPreference)
        val preferenceChangeListener =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                if (key.equals(getString(R.string.text_style))) {
                    updateListPrefSummary()
                }
                val preference = findPreference(key)
                when (preference) {
                    is SwitchPreference -> {
                        val sharedPrefs =
                            PreferenceManager.getDefaultSharedPreferences(this@CustomPreferenceActivity.applicationContext)
                        val isOn: Boolean = sharedPrefs.getBoolean(
                            themeSwitch.key,
                            false
                        )

                        if (isOn) {
                            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    private fun updateListPrefSummary() {
        val preference = findPreference(getString(R.string.text_style)) as ListPreference
        val entry = preference.entry
        preference.summary = "Current value: $entry"
    }
}