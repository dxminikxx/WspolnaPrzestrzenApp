package com.example.mozeterazsieuda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mozeterazsieuda.R
import com.example.mozeterazsieuda.Success


class LoginActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var editUser: EditText
    private lateinit var editPword: EditText
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.btn_login)
        editUser = findViewById(R.id.UserName)
        editPword = findViewById(R.id.UserPassword)
        dbHelper = DBHelper(this)

        loginBtn.setOnClickListener{
            val useredtx = editUser.text.toString()
            val passedtx = editPword.text.toString()

            if(TextUtils.isEmpty(useredtx) || TextUtils.isEmpty(passedtx)){
                Toast.makeText(this, "Add Username & Password", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkUser = dbHelper.checkuserpass(useredtx, passedtx)
                if(checkUser==true){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, Success::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Wrong Username & Password", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}

