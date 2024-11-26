package com.localization

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setLocale("la")
//        setLocale(this@MainActivity, "la")
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.text)
        textView.setText(R.string.hello_world)
        val buttonLatin: Button = findViewById(R.id.button_latin)
        buttonLatin.setOnClickListener {
            setLocale("la")
            textView.setText(R.string.hello_world)
        }
        val buttonEnglish: Button = findViewById(R.id.button_english)
        buttonEnglish.setOnClickListener {
            setLocale("en")
            textView.setText(R.string.hello_world)
        }
        val buttonRussian: Button = findViewById(R.id.button_russian)
        buttonRussian.setOnClickListener {
            setLocale("ru")
            textView.setText(R.string.hello_world)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = this@MainActivity.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

//    private val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
//
//    private fun setLocale(context: Context, language: String): Context {
//        persist(context, language)
//        return updateResourcesLegacy(context, language)
//
//    }
//
//    private fun persist(context: Context, language: String) {
//        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val editor = preferences.edit()
//        editor.putString(SELECTED_LANGUAGE, language)
//        editor.apply()
//    }
//
//    private fun updateResourcesLegacy(context: Context, language: String): Context {
//        val locale = Locale(language)
//        Locale.setDefault(locale)
//        val resources = context.resources
//        val configuration = resources.configuration
//        configuration.setLocale(locale)
//        configuration.setLayoutDirection(locale)
//        resources.updateConfiguration(configuration, resources.displayMetrics)
//        return context
//    }
}