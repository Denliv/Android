package com.camera

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.os.BundleCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var outputFileUri: Uri
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        val startForCamera =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    if (result.data != null) {
                        if (result.data!!.hasExtra("data")) {
                            imageView.setImageBitmap(result.data?.extras?.let {
                                BundleCompat.getParcelable(
                                    it,
                                    "data",
                                    Bitmap::class.java
                                )
                            })
                        }
                    } else {
                        imageView.setImageURI(outputFileUri)
                    }
                }
            }
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
//            getThumbnailPicture(startForCamera)
            saveFullImage(startForCamera)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getThumbnailPicture(start: ActivityResultLauncher<Intent>) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startIntent(intent, start)
    }

    private fun saveFullImage(start: ActivityResultLauncher<Intent>) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        val file = File(
            Environment.getExternalStorageDirectory().path + "/" +
            Environment.DIRECTORY_DCIM,
            "test.jpg"
        )
        Toast.makeText(this, file.path, Toast.LENGTH_SHORT).show()
        outputFileUri = FileProvider.getUriForFile(
            this,
            this.applicationContext.packageName + ".provider",
            file
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        startIntent(intent, start)
    }

    private fun startIntent(intent: Intent, start: ActivityResultLauncher<Intent>) {
        try {
            start.launch(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Can not take photo", Toast.LENGTH_SHORT).show()
        }
    }
}