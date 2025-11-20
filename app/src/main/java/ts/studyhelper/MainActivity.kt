package com.example.studyhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ts.studyhelper.R
import ts.studyhelper.ui.home.HomeFragment
import ts.studyhelper.ui.home.NotesFragment
import ts.studyhelper.ui.home.ScheduleFragment
import ts.studyhelper.ui.home.TasksFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)

        // Загружаем главный экран
        loadFragment(HomeFragment())

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> loadFragment(HomeFragment())
                R.id.menu_schedule -> loadFragment(ScheduleFragment())
                R.id.menu_notes -> loadFragment(NotesFragment())
                R.id.menu_tasks -> loadFragment(TasksFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
