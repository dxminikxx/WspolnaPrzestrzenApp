package com.example.mozeterazsieuda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Date

class AnnouncementFragment(val announcement_name: String = "", val date: String = "") : Fragment() {

    private lateinit var announcementName: EditText
    private lateinit var announcementDate: EditText
    private lateinit var btnAddAnnouncement: Button
    private lateinit var btnViewAnnouncement: Button
    private lateinit var DBHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_announcement, container, false)

        initView(view)
        DBHelper = DBHelper(requireContext())
        btnAddAnnouncement.setOnClickListener{addAnnouncement()}
        btnViewAnnouncement.setOnClickListener{getAnnouncement()}

        return view
    }

    private fun addAnnouncement() {
        val name = announcementName.text.toString()
        val date = announcementDate.text.toString()

        if(name.isEmpty() || date.isEmpty()){
            Toast.makeText(requireContext(), "Please enter required field", Toast.LENGTH_SHORT).show()
            clearEditText()
        }else{
            val check = DBHelper.insertAnnouncement(name, date)
            if(check){
                Toast.makeText(requireContext(), "Announcement Added", Toast.LENGTH_SHORT).show()
                clearEditText()
            }
            else{
                Toast.makeText(requireContext(), "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAnnouncement(){

    }

    private fun clearEditText(){
        announcementName.setText("")
        announcementDate.setText("")
        announcementName.requestFocus()
    }

    private fun initView(view: View) {
        announcementName = view.findViewById(R.id.announcementName)
        announcementDate = view.findViewById(R.id.announcementDate)
        btnAddAnnouncement = view.findViewById(R.id.btnAddAnnouncement)
        btnViewAnnouncement = view.findViewById(R.id.btnViewAnnouncement)
    }

}
