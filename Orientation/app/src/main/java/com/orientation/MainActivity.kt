package com.orientation

import android.content.res.Configuration
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Display
import android.view.Surface
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.orientation.R

class MainActivity : AppCompatActivity() {
    private var changeOrientationCounter = 0
    private var currentRotation = 0
    private val orientationCounterKey = "ORIENTATION_COUNTER_KEY"
    private val rotationKey = "ROTATION_KEY"
    private val displayManager: DisplayManager by lazy {
        applicationContext.getSystemService(DISPLAY_SERVICE) as DisplayManager
    }
    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) {}
        override fun onDisplayRemoved(displayId: Int) {}
        override fun onDisplayChanged(displayId: Int) {
            val difference = displayManager.getDisplay(displayId).rotation - currentRotation
            currentRotation = displayManager.getDisplay(displayId).rotation
            if (difference == 2 || difference == -2) {
                fillOrientationInfo()
            }
        }
    }

    private fun getScreenOrientation(): String {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> "PORTRAIT"
            Configuration.ORIENTATION_LANDSCAPE -> "LANDSCAPE"
            else -> "UNKNOWN"
        }
    }

    private fun getRotateOrientation(): String {
        return when (getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY)?.rotation) {
            Surface.ROTATION_0 -> "ROTATION: 0"
            Surface.ROTATION_90 -> "ROTATION: 90"
            Surface.ROTATION_180 -> "ROTATION: 180"
            Surface.ROTATION_270 -> "ROTATION: -90"
            else -> "ROTATION: UNKNOWN"
        }
    }

    private fun fillOrientationInfo() {
        var orientationText: TextView = findViewById(R.id.orientation_info)
        changeOrientationCounter++
        orientationText.text = getScreenOrientation() + "\n" + getRotateOrientation() + "\n" + changeOrientationCounter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var orientationText: TextView = findViewById(R.id.orientation_info)
        changeOrientationCounter = 0
        currentRotation = getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY)?.rotation!!
        orientationText.text = getScreenOrientation() + "\n" + getRotateOrientation() + "\n" + changeOrientationCounter
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        fillOrientationInfo()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(orientationCounterKey, changeOrientationCounter)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        changeOrientationCounter = savedInstanceState.getInt(orientationCounterKey)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        displayManager.registerDisplayListener(displayListener, Handler(Looper.getMainLooper()))
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        displayManager.unregisterDisplayListener(displayListener)
    }
}