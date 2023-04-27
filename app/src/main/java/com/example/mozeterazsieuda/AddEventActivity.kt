package com.example.mozeterazsieuda

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import java.util.*
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat

class AddEventActivity : AppCompatActivity() {

    private lateinit var  btnDatePicker: Button
    private lateinit var  btnTimePicker : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        btnDatePicker = findViewById(R.id.eventDate)
        btnTimePicker = findViewById(R.id.eventHour)

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

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        btnDatePicker.setText(sdf.format(myCalendar.time))
    }


}