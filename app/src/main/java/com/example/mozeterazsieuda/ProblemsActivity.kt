package com.example.mozeterazsieuda

import ProblemsDBHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ProblemsActivity : AppCompatActivity() {

    private lateinit var problemsDBHelper: ProblemsDBHelper
    private lateinit var etName: EditText
    private lateinit var etMessage: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problems)

        problemsDBHelper = ProblemsDBHelper(this)
        etName = findViewById(R.id.problem_name)
        etMessage = findViewById(R.id.message_problem)
        etPhone = findViewById(R.id.phone_problem)
        etEmail = findViewById(R.id.email_problem)

        val btnSend = findViewById<Button>(R.id.btn_sendProblem)
        btnSend.setOnClickListener {
            val name = etName.text.toString()
            val message = etMessage.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()

            if (name.isNotEmpty() && message.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
                val success = problemsDBHelper.addProblem(name, message, phone, email)

                if (success) {
                    Toast.makeText(this, "Problem został pomyślnie zgłoszony", Toast.LENGTH_SHORT)
                        .show()
                    clearFields()
                } else {
                    Toast.makeText(
                        this,
                        "Wystąpił błąd podczas zgłaszania problemu",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this, "Należy wypełnić wszystkie pola", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        etName.text.clear()
        etMessage.text.clear()
        etPhone.text.clear()
        etEmail.text.clear()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    // return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.item_done) {
            message("Done")
        }
        return super.onOptionsItemSelected(item)
    }

    fun message(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }
}