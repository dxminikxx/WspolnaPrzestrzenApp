package com.example.mozeterazsieuda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity() {

    private  lateinit var uname: EditText
    private lateinit var pword: EditText
    private lateinit var cpword: EditText
    private lateinit var registerBtn: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        uname = findViewById(R.id.UserName)
        pword = findViewById(R.id.UserPassword)
        cpword = findViewById(R.id.UserPasswordSecond)
        registerBtn = findViewById(R.id.button)
        db =  DBHelper(this)

        registerBtn.setOnClickListener{
            val unameText = uname.text.toString()
            val pwordText = pword.text.toString()
            val cpwordText = cpword.text.toString()
            val saveData = db.instertdata(unameText, pwordText)

            if(TextUtils.isEmpty(unameText) || TextUtils.isEmpty(pwordText) || TextUtils.isEmpty(cpwordText)){
                Toast.makeText(this, "Add Username, Password & Conform Password", Toast.LENGTH_SHORT).show()
            }
            else{
                if(pwordText.equals(cpwordText)){
                    if(saveData==true){
                        Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "User Exists", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Password Not Match", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}