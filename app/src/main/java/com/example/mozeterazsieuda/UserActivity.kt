package com.example.mozeterazsieuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val imageId = intent.getIntExtra("imageId", R.drawable.firstcontact)

    }
}
