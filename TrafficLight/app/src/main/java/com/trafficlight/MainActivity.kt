package com.trafficlight

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val buttonRed: Button = findViewById(R.id.button_red)
        val buttonYellow: Button = findViewById(R.id.button_yellow)
        val buttonGreen: Button = findViewById(R.id.button_green)
        val topLight: Button = findViewById(R.id.top_light)
        val middleLight: Button = findViewById(R.id.middle_light)
        val bottomLight: Button = findViewById(R.id.bottom_light)
        buttonRed.setOnClickListener {
            topLight.setBackgroundColor(getColor(R.color.red_button))
            middleLight.setBackgroundColor(getColor(R.color.default_light))
            bottomLight.setBackgroundColor(getColor(R.color.default_light))
        }
        buttonYellow.setOnClickListener {
            topLight.setBackgroundColor(getColor(R.color.default_light))
            middleLight.setBackgroundColor(getColor(R.color.yellow_button))
            bottomLight.setBackgroundColor(getColor(R.color.default_light))
        }
        buttonGreen.setOnClickListener {
            topLight.setBackgroundColor(getColor(R.color.default_light))
            middleLight.setBackgroundColor(getColor(R.color.default_light))
            bottomLight.setBackgroundColor(getColor(R.color.green_button))
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}