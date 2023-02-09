package com.example.sumatifroom_nurazizah_ganjil

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class ScreenSplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent (this@ScreenSplash,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

    }
}