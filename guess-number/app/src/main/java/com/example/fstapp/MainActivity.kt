package com.example.fstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGameActivity(view: android.view.View) {
        val lowerBound = findViewById<EditText>(R.id.lower_bound)
        val upperBound = findViewById<EditText>(R.id.upper_bound)

        if ((lowerBound.text.isEmpty() || upperBound.text.isEmpty()) ||
            lowerBound.text.toString().toInt() >= upperBound.text.toString().toInt()) {
            findViewById<TextView>(R.id.warnging_text)
                .text = resources.getString(R.string.warning_text)
            return
        }

        val gameActivityIntent = Intent(this, GameActivity::class.java)

        gameActivityIntent.putExtra("lowerBound", lowerBound.text.toString().toInt())
        gameActivityIntent.putExtra("upperBound", upperBound.text.toString().toInt())
        startActivity(gameActivityIntent)
    }
}