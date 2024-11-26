package com.crowcounter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private var crowCounter: Int = 0
    private var catCounter: Int = 0
    private val CAT_COUNTER = "catCounter"
    private val CROW_COUNTER = "crowCounter"
    private lateinit var textCat: TextView
    private lateinit var textCrow: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textCat = findViewById(R.id.numberOfCats)
        textCrow = findViewById(R.id.numberOfCrows)
        val buttonCat: Button = findViewById(R.id.buttonCatCounter)
        val buttonCrow: Button = findViewById(R.id.buttonCrowCounter)
        buttonCat.setOnClickListener {
            textCat.text = "${++catCounter}"
        }
        buttonCrow.setOnClickListener {
            textCrow.text = "${++crowCounter}"
        }
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
        val editor = prefs.edit()
        editor.putInt(CAT_COUNTER, catCounter).apply()
        editor.putInt(CROW_COUNTER, crowCounter).apply()
    }

    override fun onResume() {
        super.onResume()
        if(prefs.contains(CAT_COUNTER)){
            catCounter = prefs.getInt(CAT_COUNTER, 0)
            textCat.text = "$catCounter"
        }
        if(prefs.contains(CROW_COUNTER)){
            crowCounter = prefs.getInt(CROW_COUNTER, 0)
            textCrow.text = "$crowCounter"
        }
    }
}