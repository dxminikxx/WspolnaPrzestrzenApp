package com.example.mozeterazsieuda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SettingsFragment : Fragment() {

    private lateinit var btn_carPark: Button
    private lateinit var btn_survey: Button
    private lateinit var btn_problems: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        btn_carPark = view.findViewById(R.id.btn_carPark)
        btn_survey = view.findViewById(R.id.btn_survey)
        btn_problems = view.findViewById(R.id.btn_problems)
        btn_carPark.setOnClickListener {
            val intent = Intent(activity, ParkingActivity::class.java)
            startActivity(intent)
        }
        btn_survey.setOnClickListener {
            val intent = Intent(activity, SurveyActivity::class.java)
            startActivity(intent)
        }
        btn_problems.setOnClickListener {
            val intent = Intent(activity, ProblemsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}