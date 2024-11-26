package com.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date

class TimeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val message = StringBuilder("Current time: ")
        val formatter: Format = SimpleDateFormat("hh:mm:ss a")
        message.append(formatter.format(Date()))
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}