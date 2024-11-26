package com.notebook

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class FileActivity : AppCompatActivity() {
    private lateinit var fileName: String
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_file)
        val intent = intent
        fileName = intent.getStringExtra("fileName").toString()
        editText = findViewById(R.id.editText)
        openFile(fileName)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.file)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_file, menu)
        supportActionBar!!.title = fileName
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open -> {
                openFile(fileName)
                true
            }
            R.id.action_save -> {
                saveFile(fileName)
                true
            }
            R.id.action_home -> {
                finish()
                true
            }
            else -> true
        }
    }

    private fun openFile(fileName: String) {
        val textFromFile = File(applicationContext.filesDir, fileName).bufferedReader().use { it.readText(); }
        editText.setText(textFromFile)
    }

    private fun saveFile(fileName: String) {
        applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(editText.text.toString().toByteArray())
        }
        Toast.makeText(applicationContext, "File saved", Toast.LENGTH_SHORT).show()
    }
}