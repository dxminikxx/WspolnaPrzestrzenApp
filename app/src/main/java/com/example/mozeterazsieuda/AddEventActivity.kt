package com.example.mozeterazsieuda

import EventDBHelper
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*
import java.text.SimpleDateFormat

class AddEventActivity : AppCompatActivity() {

    private lateinit var  btnDatePicker: Button
    private lateinit var  btnTimePicker : Button
    private lateinit var eventDBHelper: EventDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        btnDatePicker = findViewById(R.id.eventDate)
        btnTimePicker = findViewById(R.id.eventHour)
        eventDBHelper = EventDBHelper(this)

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }

        btnDatePicker.setOnClickListener {
            DatePickerDialog(
                this, datePicker, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnTimePicker.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{
                    view, hourOfDay, minute ->
                btnTimePicker.setText("$hourOfDay:$minute")
            }, startHour, startMinute, false).show()
        }
    }

    fun sendEventButtonClicked(view: View) {
        val success = eventDBHelper.addEvent("Nazwa eventu", "Data", "Godzina")

        if (success) {
            startActivity(intent)
            Toast.makeText(this, "Poprawnie dodano event", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wystąpił błąd podczas dodawania eventu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        btnDatePicker.setText(sdf.format(myCalendar.time))
    }
}
