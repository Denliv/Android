package com.themes

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.TextViewCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var button18: Button = findViewById(R.id.button_size_18)
        var button38: Button = findViewById(R.id.button_size_38)
        var text: TextView = findViewById(R.id.text_example)
        button18.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("Set text size to 18?")
                .setPositiveButton("YES") { dialog, which ->
                    text.setTextAppearance(R.style.Size_18)
                }
                .setNegativeButton("NO") { dialog, which ->

                }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        button38.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("Set text size to 18?")
                .setPositiveButton("YES") { dialog, which ->
                    text.setTextAppearance(R.style.Size_38)
                }
                .setNegativeButton("NO") { dialog, which ->

                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}