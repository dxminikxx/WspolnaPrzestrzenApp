package com.example.mozeterazsieuda

import AnnouncementFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.mozeterazsieuda.HomeFragment
import com.example.mozeterazsieuda.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Success : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        navigationView = findViewById(R.id.bottom_navigation)
        supportFragmentManager.beginTransaction().replace(R.id.fl_wrapper, HomeFragment()).commit()
        navigationView.selectedItemId = R.id.nav_home

        navigationView.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> fragment = HomeFragment()
                R.id.nav_calendar -> fragment = CalendarFragment()
                R.id.nav_contact_list -> fragment = ContactFragment()
                R.id.nav_settings -> fragment = SettingsFragment()
                R.id.nav_announcement -> fragment = AnnouncementFragment()
            }


            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fl_wrapper, fragment).commit()
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }

    }
}
