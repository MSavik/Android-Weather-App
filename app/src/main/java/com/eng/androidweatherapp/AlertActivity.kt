package com.eng.androidweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eng.androidweatherapp.DataGlobal
import com.eng.androidweatherapp.MainActivity
import com.eng.androidweatherapp.R

class AlertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        DataGlobal.alertBackPressed = true
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}