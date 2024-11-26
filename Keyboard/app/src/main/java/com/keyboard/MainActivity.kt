package com.keyboard

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Exit Alert")
                    .setMessage("Are you sure you want to exit the program?")
                    .setPositiveButton("Yes") { dialog, whichButton ->
                        finish()
                    }
                    .setNegativeButton("No") { dialog, whichButton ->
                    }
                    .setNeutralButton("Maybe(50% chance to exit)") { dialog, whichButton ->
                        if ((0..1).random() == 0) {
                            finish()
                        }
                    }
                    .show()
            }
        })
        val editText: EditText = findViewById(R.id.edit_text)
        editText.inputType = InputType.TYPE_CLASS_DATETIME
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onUserLeaveHint() {
        val toast = Toast.makeText(applicationContext, "HOME button is pressed", Toast.LENGTH_SHORT)
        toast.show()
        super.onUserLeaveHint()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                val parent: View = findViewById(R.id.main)
                parent.setBackgroundColor(Color.RED)
                return true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                val parent: View = findViewById(R.id.main)
                parent.setBackgroundColor(Color.BLUE)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}