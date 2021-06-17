package com.example.aroundtheworld.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.aroundtheworld.databinding.ActivitySplashScreenBinding

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadSplashScreen()
    }

    private fun loadSplashScreen(){
        Handler().postDelayed({
            // we used the postDelayed(Runnable, time) method to send a message with a delayed time.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },SPLASH_TIME_OUT)
    }

}