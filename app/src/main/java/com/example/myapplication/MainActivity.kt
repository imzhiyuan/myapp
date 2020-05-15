package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SignUp.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
        }
        Login.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

    }
}
