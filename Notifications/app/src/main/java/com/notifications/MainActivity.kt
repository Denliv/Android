package com.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {
    companion object {
        const val CHANNEL_ID = "channelId"
        const val CHANNEL_NAME = "channelName"
        const val CHANNEL_DESCRIPTION = "channelDescription"
        var NOTIFY_ID = 1
        const val MAX_NUMBER_OF_NOTIFICATIONS = 5
        const val BIG_IMG_NOTIFY_ID = 2
        const val BIG_TEXT_NOTIFY_ID = 3
    }

    private lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val emptyIntent = PendingIntent.getActivity(applicationContext, 0, Intent(), PendingIntent.FLAG_IMMUTABLE)
        val buttonSend: Button = findViewById(R.id.button_send)
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()
        jojoSound = loadSound("jojo.mp3")
        buttonSend.setOnClickListener {
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("ATTENTION")
                .setContentText("Return the money?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.jojo
                    )
                )
                .addAction(R.drawable.ic_yes, "YES", emptyIntent)
                .addAction(R.drawable.ic_no, "NO", emptyIntent)
            try {
                notificationManager.notify((NOTIFY_ID++) % MAX_NUMBER_OF_NOTIFICATIONS, builder.build())
                playSound(jojoSound)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
        val buttonClear: Button = findViewById(R.id.button_clear)
        buttonClear.setOnClickListener {
            notificationManager.cancelAll()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private var jojoSound: Int = 0
    private var streamID = 0
    private lateinit var soundPool: SoundPool

    private fun playSound(sound: Int) {
        if (sound > 0) {
            streamID = soundPool.play(sound, 1F, 1F, 1, 0, 1F)
        }
    }

    private fun loadSound(fileName: String): Int {
        val fileDescriptor: AssetFileDescriptor = try {
            application.assets.openFd(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(
                applicationContext, "Can not load the file '$fileName'", Toast.LENGTH_SHORT
            ).show()
            return -1
        }
        return soundPool.load(fileDescriptor, 1)
    }
}