package com.display

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.Display
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private fun getDisplayBrightness(): String? {
        try {
            val curBrightnessValue = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS)
            return "$curBrightnessValue"
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
            return null
        }
    }

    private fun getScreenSizeCategory(): String {
        return when (resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK) {
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> "XLarge screen"
            Configuration.SCREENLAYOUT_SIZE_LARGE -> "Large screen"
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> "Normal size screen"
            Configuration.SCREENLAYOUT_SIZE_SMALL -> "Small size screen"
            Configuration.SCREENLAYOUT_SIZE_UNDEFINED -> "Undefined screen size"
            else -> "Error"
        }
    }

    private fun getWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    private fun getHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    private fun getDisplayInfo():String {
        return  "Width: " + getWidth() + "\n" +
                "Height: " + getHeight() + "\n" +
                "Screen type: " + getScreenSizeCategory() + "\n" +
                "Display brightness: " + getDisplayBrightness()
    }

    private lateinit var displayInfo: TextView
    private val updateInfoTime: Long = 10
    private val handler = Handler(Looper.getMainLooper())
    private val updateInfo: Runnable = object : Runnable {
        override fun run() {
            displayInfo.text = getDisplayInfo()
            handler.postDelayed(this, updateInfoTime)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val buttonDisplaySettings: Button = findViewById(R.id.button_display_settings)
        buttonDisplaySettings.setOnClickListener {
            val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        displayInfo = findViewById(R.id.display_info)
        displayInfo.text = getDisplayInfo()
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(updateInfo, updateInfoTime)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}