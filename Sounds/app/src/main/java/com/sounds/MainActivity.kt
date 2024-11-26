package com.sounds

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var fireSound: Int = 0
    private var helpSound: Int = 0
    private var lebovskiSound: Int = 0
    private var jojoSound: Int = 0
    private var streamID = 0
    private lateinit var soundPool: SoundPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val fireImage: ImageView = findViewById(R.id.image_fire)
        val helpImage: ImageView = findViewById(R.id.image_help)
        val lebovskiImage: ImageView = findViewById(R.id.image_lebovski)
        val jojoImage: ImageView = findViewById(R.id.image_jojo)
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()
        fireSound = loadSound("fire.mp3")
        helpSound = loadSound("help.mp3")
        lebovskiSound = loadSound("lebovski.mp3")
        jojoSound = loadSound("jojo.mp3")
        fireImage.setOnClickListener{playSound(fireSound)}
        helpImage.setOnClickListener{playSound(helpSound)}
        lebovskiImage.setOnClickListener{playSound(lebovskiSound)}
        jojoImage.setOnClickListener{playSound(jojoSound)}
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

    override fun onResume() {
        super.onResume()
        fireSound = loadSound("fire.mp3")
        helpSound = loadSound("help.mp3")
        lebovskiSound = loadSound("lebovski.mp3")
        jojoSound = loadSound("jojo.mp3")
    }

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