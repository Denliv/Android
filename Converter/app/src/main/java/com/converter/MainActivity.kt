package com.converter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
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
        val meterButton: RadioButton = findViewById(R.id.to_meters)
        val kilometerButton: RadioButton = findViewById(R.id.to_kilometers)
        val daniilButton: RadioButton = findViewById(R.id.to_daniil_teslov)
        val convertButton: Button = findViewById(R.id.button_convert)
        val inputText: EditText = findViewById(R.id.input_text)
        convertButton.setOnClickListener {
            if (inputText.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter data to convert", Toast.LENGTH_LONG).show()
            }
            else {
                val inputValue = inputText.text.toString().toFloat()
                if (meterButton.isChecked) {
                    inputText.setText(convertToMeter(inputValue).toString())
                }
                else if (kilometerButton.isChecked) {
                    inputText.setText(convertToKilometer(inputValue).toString())
                }
                else if (daniilButton.isChecked) {
                    inputText.setText(convertToDaniilTeslov(inputValue).toString())
                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun convertToMeter(data: Float): Float = (data / 100)

    private fun convertToKilometer(data: Float): Float = (data / 100000)

    private fun convertToDaniilTeslov(data: Float): Float = (data / 170)
}