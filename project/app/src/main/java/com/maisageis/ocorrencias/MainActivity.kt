package com.maisageis.ocorrencias

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.maisageis.ocorrencias.ui.item01.Blank01Fragment
import com.maisageis.ocorrencias.ui.item02.BlankFragment
import com.maisageis.ocorrencias.ui.login.LoginActivity
import com.maisageis.ocorrencias.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_songs -> {
                toolbar.title = "Songs"
                val songsFragment = BlankFragment.newInstance()
                openFragment(songsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_albums -> {

                toolbar.title = "020202"
                val songsFragment = Blank01Fragment.newInstance()
                openFragment(songsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_artists -> {

                toolbar.title = "030303"
                val songsFragment = BlankFragment.newInstance()
                openFragment(songsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}