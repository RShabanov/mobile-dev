package com.example.fstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    private var lowerBound: Int = Int.MIN_VALUE
    private var upperBound: Int = Int.MAX_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        lowerBound = intent.getIntExtra("lowerBound", 0)
        upperBound = intent.getIntExtra("upperBound", 0)

        changeText(resources.getString(R.string.guess_range) + " ${getMean(upperBound, lowerBound)}")
    }

    private fun changeText(text: String) {
        findViewById<TextView>(R.id.text).text = text
    }

    private fun getMean(lhs: Int, rhs: Int): Int = (lhs + rhs) / 2

    fun onYesOrNoBtn(view: android.view.View) {

        when (view.id) {
            R.id.yes -> {
                if ((upperBound - lowerBound) <= 1) {
                    findViewById<Button>(R.id.yes).isEnabled = false
                    findViewById<Button>(R.id.no).isEnabled = false
                } else lowerBound = getMean(upperBound, lowerBound)
            }
            R.id.no -> {
                if ((upperBound - lowerBound) <= 1) {
                    upperBound = lowerBound
                    findViewById<Button>(R.id.yes).isEnabled = false
                    findViewById<Button>(R.id.no).isEnabled = false
                } else upperBound = getMean(upperBound, lowerBound)
            }
        }

        val text: String = if ((upperBound - lowerBound) > 1) {
            resources.getString(R.string.guess_range) + " ${getMean(upperBound, lowerBound)}?"
        } else {
            resources.getString(R.string.guess_number) + " ${upperBound}!"
        }

        changeText(text)
    }

    fun onRestart(view: android.view.View) {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(mainActivityIntent)
    }
}