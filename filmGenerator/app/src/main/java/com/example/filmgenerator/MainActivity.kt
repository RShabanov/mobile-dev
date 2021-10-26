package com.example.filmgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGenerateMovie(view: android.view.View) {
        val movieGeneratorActivity = Intent(this, MovieActivity::class.java)
        startActivity(movieGeneratorActivity)
    }
}