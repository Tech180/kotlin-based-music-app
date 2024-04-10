package com.example.musicify.ui.menu

import com.example.musicify.universal.universal_methods

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.musicify.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class Drawer : universal_methods() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBar: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer)
        

        drawerLayout = findViewById(R.id.drawer_layout)
        actionBar = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        // Set up the MaterialToolbar
        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)

        // Set the navigation icon click listener
        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
        }


        // Set up NavigationView
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.inflateMenu(R.menu.drawer_menu)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle Home click
                    true
                }
                R.id.nav_library -> {
                    // Handle Library click
                    true
                }
                else -> false
            }

            menuItem.isChecked = true
            drawerLayout.close()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBar.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
