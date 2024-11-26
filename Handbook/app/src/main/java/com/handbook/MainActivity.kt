package com.handbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private val titles = arrayOf(
        "Глава 1. Введение в Entity Framework Core",
        "1.1 Введение в Entity Framework Core",
        "1.2 Первое приложение на EF Core",
        "1.3 Подключение к существующей базе данных",
        "1.4 Управление базой данных",
        "1.5 Основные операции с данными. CRUD",
        "1.6 Конфигурация подключения",
        "1.7 Логгирование операций",
        "1.8 Управление схемой БД и миграции",
        "Secret"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, titles)
        listView.isTextFilterEnabled = true
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (position != 0) {
                val intent = Intent()
                intent.setClass(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("title", position)
                startActivity(intent)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}