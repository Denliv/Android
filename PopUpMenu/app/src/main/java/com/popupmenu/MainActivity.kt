package com.popupmenu

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)
        val imageView = findViewById<ImageView>(R.id.imageView)
        button.setOnClickListener {
            showPopupMenu(button)
        }
        textView.setOnClickListener {
            showPopupMenu(textView)
        }
        imageView.setOnClickListener {
            showPopupMenu(imageView)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_main)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.menu1 -> {
                        Toast.makeText(applicationContext, "2+2=4", Toast.LENGTH_SHORT).show()
                        return true
                    }
                    R.id.menu2 -> {
                        Toast.makeText(applicationContext, "3+3=6", Toast.LENGTH_SHORT).show()
                        return true
                    }
                    R.id.submenu -> {
                        Toast.makeText(applicationContext, "999*0=0", Toast.LENGTH_SHORT).show()
                        return true
                    }
                    else -> return false
                }
            }
        })
        popupMenu.show()
    }
}