package com.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MessageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,
            "Message detected: " + intent.getStringExtra("com.broadcast.send.message"),
            Toast.LENGTH_SHORT).show()
    }
}