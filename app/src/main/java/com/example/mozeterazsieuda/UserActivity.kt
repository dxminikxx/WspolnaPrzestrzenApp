package com.example.mozeterazsieuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val imageId = intent.getIntExtra("imageId", R.drawable.firstcontact)

        val nameTextView = findViewById<TextView>(R.id.user_name)
        val phoneTextView = findViewById<TextView>(R.id.user_phone)
        val imageImageView = findViewById<ImageView>(R.id.profile_image)

        nameTextView.text = name
        phoneTextView.text = phone
        imageImageView.setImageResource(imageId)
    }
}
