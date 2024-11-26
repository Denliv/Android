package com.areyousleeping

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var phrases: Array<String> = arrayOf(
        "Вставай, Наташ",
        "Вставай, мы там всё уронили",
        "Мы уронили вообще всё, Наташ, честно",
        "Сервер упал, Наташ",
        "Мы удалили репозиторий с проектом, Наташ",
        "Тебя вызывают на работу, Наташ",
        "Пиво закончилось, Наташ"
    )

    private fun catClick(cat: ImageView, textView: TextView, phrases: Array<String>) {
        val handler = Handler(Looper.getMainLooper())
        cat.setOnClickListener {
            handler.removeCallbacksAndMessages(null)
            val newText = phrases[IntRange(0, phrases.size - 1).random()]
            textView.text = newText
            handler.postDelayed({ textView.text = getString(R.string.silence) }, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val catRightTop: ImageView = findViewById(R.id.cat_right_top)
        val catRightBottom: ImageView = findViewById(R.id.cat_right_bottom)
        val catLeftBottom: ImageView = findViewById(R.id.cat_left_bottom)
        val catLeftMiddle: ImageView = findViewById(R.id.cat_left_middle)
        val textCatLeftMiddle: TextView = findViewById(R.id.text_cat_left_middle)
        val textCatLeftBottom: TextView = findViewById(R.id.text_cat_left_bottom)
        val textCatRightBottom: TextView = findViewById(R.id.text_cat_right_bottom)
        val textCatRightTop: TextView = findViewById(R.id.text_cat_right_top)
        catClick(catRightTop, textCatRightTop, phrases)
        catClick(catRightBottom, textCatRightBottom, phrases)
        catClick(catLeftBottom, textCatLeftBottom, phrases)
        catClick(catLeftMiddle, textCatLeftMiddle, phrases)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}