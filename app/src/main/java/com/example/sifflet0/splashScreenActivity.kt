package com.example.sifflet0

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class splashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spash_screen)
        supportActionBar?.hide()
        Handler().postDelayed ({
            val i = Intent(this, login::class.java)
            startActivity(i)
            finish()

        },3000)
    }
}