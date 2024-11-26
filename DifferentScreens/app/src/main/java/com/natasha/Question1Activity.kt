package com.natasha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Question1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question1)
        val answerButton: Button = findViewById(R.id.question1_answer_button)
        val radioGroup: RadioGroup = findViewById(R.id.answer_group)
        val intent = Intent()
        radioGroup.setOnCheckedChangeListener { _, optionId ->
            when (optionId) {
                R.id.answer1 -> intent.putExtra("question1_score", 0)
                R.id.answer2 -> intent.putExtra("question1_score", 0)
                R.id.answer3 -> intent.putExtra("question1_score", 1)
                R.id.answer4 -> intent.putExtra("question1_score", 0)
            }

        }
        answerButton.setOnClickListener {
            setResult(RESULT_OK, intent)
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}