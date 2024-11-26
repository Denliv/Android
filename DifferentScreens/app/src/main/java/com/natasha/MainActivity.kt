package com.natasha

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val question1: ImageView = findViewById(R.id.question_1)
        val signIn: TextView = findViewById(R.id.sign_in)
        val globalScore: TextView = findViewById(R.id.global_score_number)
        val question1Score: TextView = findViewById(R.id.question_1_score)
        val startForSignIn =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val temp = result.data?.getStringExtra("username")
                    if (temp == null || temp == "") {
                        signIn.text = "Anonymous"
                    }
                    else {
                        signIn.text = temp
                    }
                }
            }
        val startForQuestion1 =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val temp = result.data?.getIntExtra("question1_score", 0)
                    val questionScoreParts: MutableList<String> =
                        question1Score.text.toString().split("/").toMutableList()
                    globalScore.text = (globalScore.text.toString().toInt() + (temp ?: 0) - questionScoreParts[0].toInt()).toString()
                    questionScoreParts[0] = temp.toString()
                    question1Score.text = questionScoreParts.joinToString("/")
                }
            }
        signIn.setOnClickListener {
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            startForSignIn.launch(intent)
        }
        question1.setOnClickListener {
            val intent = Intent(this@MainActivity, Question1Activity::class.java)
            startForQuestion1.launch(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}