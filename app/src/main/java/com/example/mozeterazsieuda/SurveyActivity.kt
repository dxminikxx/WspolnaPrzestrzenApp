package com.example.mozeterazsieuda

import SurveyDBHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

class SurveyActivity : AppCompatActivity() {

    private lateinit var dbHelper: SurveyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        dbHelper = SurveyDBHelper(this)

        val radioButtonsGroup1 = listOf(
            findViewById<RadioButton>(R.id.RadioButton1),
            findViewById<RadioButton>(R.id.RadioButton2),
            findViewById<RadioButton>(R.id.RadioButton3)
        )
        val radioButtonsGroup2 = listOf(
            findViewById<RadioButton>(R.id.RadioButton4),
            findViewById<RadioButton>(R.id.RadioButton5),
            findViewById<RadioButton>(R.id.RadioButton6)
        )
        val radioButtonsGroup3 = listOf(
            findViewById<RadioButton>(R.id.RadioButton7),
            findViewById<RadioButton>(R.id.RadioButton8),
            findViewById<RadioButton>(R.id.RadioButton9)
        )
        val radioButtonsGroup4 = listOf(
            findViewById<RadioButton>(R.id.RadioButton10),
            findViewById<RadioButton>(R.id.RadioButton11),
            findViewById<RadioButton>(R.id.RadioButton12)
        )

        val radioButtonsGroup5 = listOf(
            findViewById<RadioButton>(R.id.RadioButton13),
            findViewById<RadioButton>(R.id.RadioButton14),
            findViewById<RadioButton>(R.id.RadioButton15)
        )

        val saveButton: Button = findViewById(R.id.save_button)

        val radioButtons = listOf(radioButtonsGroup1, radioButtonsGroup2, radioButtonsGroup3, radioButtonsGroup4, radioButtonsGroup5)

        radioButtons.forEach { group ->
            group.forEach { radioButton ->
                radioButton.setOnClickListener {
                    group.forEach { rb ->
                        if (rb != radioButton) {
                            rb.isChecked = false
                        }
                    }
                }
            }
        }

        saveButton.setOnClickListener {
            var selectedOption: String? = null

            radioButtons.forEachIndexed { index, group ->
                val checkedButton = group.firstOrNull { it.isChecked }
                if (checkedButton != null) {
                    selectedOption = "Option ${index + 1}"
                    return@forEachIndexed
                }
            }

            // Sprawdź, czy użytkownik wybrał jedną z opcji
            if (selectedOption != null) {
                val success = dbHelper.insertSurveyData(selectedOption!!)
                if (success) {
                    radioButtons.forEach { group ->
                        group.forEach { radioButton ->
                            radioButton.isChecked = false
                        }
                    }
                    selectedOption = null
                } else {
                    // Wystąpił błąd podczas zapisywania danych
                }
            } else {
                // Użytkownik nie wybrał żadnej opcji
            }
        }
    }
    fun sendButtonClicked(view: View) {
        val intent = Intent(this, SuccessSurvey::class.java)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }




}
