package com.example.mozeterazsieuda

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment

class ContactFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        listView = view.findViewById(R.id.listView)

        val imageId = intArrayOf(
            R.drawable.firstcontact, R.drawable.secondcontact, R.drawable.thirdcontact,
            R.drawable.fourthcontact, R.drawable.fifthcontact
        )

        val name = arrayOf(
            "Estate administrator",
            "Neighborhood councillor",
            "Energy emergency",
            "Public transport",
            "Post office"
        )

        val phoneNumber = arrayOf(
            "258-369-147", "789-456-123", "159-357-482",
            "741-852-963", "268-157-248"
        )

        userArrayList = ArrayList()

        for(i in name.indices){
            val user = User(name[i], phoneNumber[i], imageId[i])
            userArrayList.add(user)
        }

        val adapter = MyAdapter(requireContext() as Activity, userArrayList)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val name = name[position]
            val phone = phoneNumber[position]
            val imageId = imageId[position]

            val i = Intent(requireContext(),UserActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("phone", phone)
            i.putExtra("imageId", imageId)
            startActivity(i)
        }

        return view
    }
}
