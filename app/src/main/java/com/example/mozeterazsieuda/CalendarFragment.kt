package com.example.mozeterazsieuda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class CalendarFragment : Fragment() {

    private lateinit var btn_addEvent: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        btn_addEvent = view.findViewById(R.id.btn_addEvent)
        btn_addEvent.setOnClickListener {
            val intent = Intent(activity, AddEventActivity::class.java)
            startActivity(intent)
        }

        return view
    }


}