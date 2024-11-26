package com.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        val ACTION_SEND_KEY: String = "com.broadcast.action.send"
        val ACTION_TIME_KEY: String = "com.broadcast.action.time"
    }
    private lateinit var messageReciever: MessageReceiver
    private lateinit var timeReciever: TimeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        messageReciever = MessageReceiver()
        IntentFilter(ACTION_SEND_KEY). also {
            registerReceiver(messageReciever, it)
        }
        val message: EditText = findViewById(R.id.edit_message)
        val sendButton: Button = findViewById(R.id.button_send)
        val timeButton: Button = findViewById(R.id.button_time)
        val clearButton: Button = findViewById(R.id.button_clear)
        sendButton.setOnClickListener {
            val intent = Intent()
            intent.action = ACTION_SEND_KEY
            intent.putExtra("com.broadcast.send.message", message.text.toString())
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            sendBroadcast(intent)
        }
        clearButton.setOnClickListener {
            message.text.clear()
        }
        timeButton.setOnClickListener {
            timeReciever = TimeReceiver()
            IntentFilter("android.intent.action.TIME_TICK"). also {
                registerReceiver(timeReciever, it)
            }
            Toast.makeText(applicationContext, "Time receiver is on", Toast.LENGTH_SHORT).show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(messageReciever)
        unregisterReceiver(timeReciever)
        Toast.makeText(applicationContext, "Time receiver is off", Toast.LENGTH_SHORT).show()
    }
}