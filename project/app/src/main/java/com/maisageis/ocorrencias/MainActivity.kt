package com.maisageis.ocorrencias

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.maisageis.ocorrencias.ui.home.HomeFragment
import com.maisageis.ocorrencias.ui.location.LocationFragment
import com.maisageis.ocorrencias.ui.maps.MapsFragment
import com.maisageis.ocorrencias.ui.mydata.MyDataFragment

class MainActivity : AppCompatActivity() {

    private val REQUEST_CHECK_SETTINGS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }

        supportActionBar?.hide()
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        permissionGeo()
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val songsFragment = HomeFragment.newInstance()
                    openFragment(songsFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_maps -> {
                    val songsFragment = MapsFragment.newInstance()
                    openFragment(songsFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_location -> {
                    val songsFragment = LocationFragment.newInstance()
                    openFragment(songsFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_user -> {
                    val songsFragment = MyDataFragment.newInstance()
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


    private fun permissionGeo() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_CHECK_SETTINGS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1 -> {
                if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "É necessário permissão de localização", Toast.LENGTH_LONG)
                        .show()
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            when (resultCode) {

                Activity.RESULT_OK -> {
                    var teste = "xxx"
                }
                Activity.RESULT_CANCELED -> {
                    var teste = "xxx"
                }
            }
        }
    }
}