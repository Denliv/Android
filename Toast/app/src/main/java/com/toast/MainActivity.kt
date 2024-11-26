package com.toast

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val buttonToast: Button = findViewById(R.id.button_toast)
        buttonToast.setOnClickListener {
            val toastDuration = Toast.LENGTH_SHORT
            val toastText = "I AM A TOAST"
            val toastTextView = TextView(this)
            toastTextView.text = toastText
            toastTextView.gravity = Gravity.CENTER_HORIZONTAL
            val toastImage = ImageView(this)
            toastImage.setImageResource(R.drawable.toast)
            val linearLayout: LinearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.addView(toastTextView)
            linearLayout.addView(toastImage)
            val toast = Toast(applicationContext)
            toast.view = linearLayout
            toast.duration = toastDuration
            toast.show()
//            val inflator = layoutInflater
//            val container = findViewById<ViewGroup>(R.id.custom_toast_container)
//            val layout: View = inflator.inflate(R.layout.toast, linearLayout)
//            with (Toast(applicationContext)) {
//                duration = toastDuration
//                view = layout
//                show()
//            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}