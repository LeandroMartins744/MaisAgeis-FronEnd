package com.maisageis.ocorrencias.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LocationStatus(private val context: Context) {

    private var locationManager : LocationManager = context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
    private lateinit var locationListener: LocationListener
    fun configurationService(result: (Location) -> Unit) {
        try {
            locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    result(location)
                }

                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0,
                0f,
                locationListener
            )
        } catch (ex: SecurityException) {
            Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    fun stopService(){
        locationManager.removeUpdates(locationListener)
    }
}