package com.example.rickandmortyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 2500)

        val prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val isFirstStart = prefs.getBoolean("isFirstStart", true)
        val welcomeMessage = findViewById<TextView>(R.id.welcome_message)

        if (isFirstStart) {
            prefs.edit().putBoolean("isFirstStart", false).apply()
            welcomeMessage.text = "Welcome!"
        } else {
            welcomeMessage.text = "Hello!"
        }
        supportActionBar?.hide()
    }
}