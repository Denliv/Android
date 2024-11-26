package com.notebook

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import java.io.File


class MainActivity : AppCompatActivity() {
    private var fileNames: ArrayList<String> = ArrayList(0)
    private lateinit var listOfFiles: ListView
    private lateinit var prefs: SharedPreferences
    private val FILE_NAMES = "fileNames"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val buttonCreate: Button = findViewById(R.id.button_create_file)
        val buttonDelete: Button = findViewById(R.id.button_delete_file)
        listOfFiles = findViewById(R.id.file_list)
        buttonCreate.setOnClickListener {
            val newFileEditText: String = (findViewById<EditText>(R.id.new_file_text)!!).text.toString() + ".txt"
            if (newFileEditText != ".txt" && !fileNames.contains(newFileEditText)) {
                fileNames.add(newFileEditText)
                val newFileRecord = TextView(this)
                newFileRecord.text = newFileEditText
                listOfFiles.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames.toArray().reversed())
                File(applicationContext.filesDir, newFileEditText).createNewFile()
            }
        }
        buttonDelete.setOnClickListener {
            val deleteFileEditText: String = (findViewById<EditText>(R.id.new_file_text)!!).text.toString() + ".txt"
            if (deleteFileEditText != ".txt" && fileNames.contains(deleteFileEditText)) {
                fileNames.remove(deleteFileEditText)
                listOfFiles.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames.toArray().reversed())
                File(applicationContext.filesDir, deleteFileEditText).delete()
                val editor = prefs.edit()
                editor.putString(FILE_NAMES, fileNames.joinToString("|")).apply()
                if (fileNames.isEmpty()) {
                    editor.remove(FILE_NAMES).commit()
                }
            }
        }
        listOfFiles.adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, fileNames.toArray().reversed())
        listOfFiles.isTextFilterEnabled = true
        listOfFiles.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent()
            intent.setClass(this@MainActivity, FileActivity::class.java)
            intent.putExtra("fileName", (listOfFiles[position] as TextView).text)
            startActivity(intent)
        }
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPause() {
        super.onPause()
        val editor = prefs.edit()
        if (fileNames.isNotEmpty()) {
            editor.putString(FILE_NAMES, fileNames.joinToString("|")).apply()
        }
    }

    override fun onResume() {
        super.onResume()
        if(prefs.contains(FILE_NAMES)) {
            fileNames = ArrayList(prefs.getString(FILE_NAMES, "")?.split("|")!!)
            listOfFiles.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames.toArray().reversed())
        }
    }
}