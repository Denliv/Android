package com.dialog

import android.os.Bundle
import android.widget.Button
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
        val dialogButton: Button = findViewById(R.id.dialog_button)
        dialogButton.setOnClickListener {
            val customDialogFragment = CustomDialogFragment()
            val manager = supportFragmentManager
            customDialogFragment.show(manager, "CustomDialog")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun neutralClicked() {
        Toast.makeText(
            this,
            "Ok",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun positiveClicked() {
        Toast.makeText(
            this,
            "I agree",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun negativeClicked() {
        Toast.makeText(
            this,
            "I disagree",
            Toast.LENGTH_SHORT
        ).show()
    }
}