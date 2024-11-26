package com.menu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var background: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        background = textView.parent as ConstraintLayout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_water -> {
                textView.text = "You choose WATER!"
                background.background = getDrawable(R.drawable.water)
            }
            R.id.action_earth -> {
                textView.text = "You choose EARTH!"
                background.background = getDrawable(R.drawable.earth)
            }
            R.id.action_fire -> {
                textView.text = "You choose FIRE!"
                background.background = getDrawable(R.drawable.fire)
            }
            R.id.action_air -> {
                textView.text = "You choose AIR!"
                background.background = getDrawable(R.drawable.air)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}