package com.maisageis.ocorrencias.ui.maps

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.location.LocationFragment
import com.maisageis.ocorrencias.util.LocationStatus
import com.maisageis.ocorrencias.util.ToastAlert
import com.maisageis.ocorrencias.util.setAnimeView
import org.koin.android.ext.android.inject

class MyMapsFragment : Fragment(), GoogleMap.OnMarkerClickListener {

    private val locationStatus: LocationStatus by inject()
    private lateinit var mapFragment: SupportMapFragment

    private lateinit var details: ConstraintLayout
    private lateinit var include: View
    private lateinit var searchButton: FloatingActionButton

   /* private const val PLACE_PICKER_REQUEST = 3

    private fun setLocation() {
        locationStatus.configurationService {
            loadData(it.latitude, it.longitude)
            locationStatus.stopService()
        }
    }

    private fun loadData(latitude: Double, longitude: Double) {
        mapFragment.getMapAsync(OnMapReadyCallback { googleMap ->
            val myLocation = LatLng(latitude, longitude)
            var marker = MarkerOptions()
            marker.position(myLocation)
            marker.title("titulo")
            marker.snippet("sdjsdfh jkssdhfjksdfh\nsdjsdfh jkssdhfjksdfh\nsdjsdfh jkssdhfjksdfh\nsdjsdfh jkssdhfjksdfh")
           //.position(myLocation).title("Marker in Sydney")

            googleMap.addMarker(marker)

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15F))
            //googleMap.moveCamera(CameraUpdateFactory.zoomTo(15F))
            googleMap.setOnMarkerClickListener(this);
        })

    }

    private fun placeMarkerOnMap(location: LatLng) {
        mapFragment.getMapAsync(OnMapReadyCallback { googleMap ->
            val markerOptions = MarkerOptions().position(location)
            googleMap.addMarker(markerOptions)
        })
    }

    companion object {
        fun newInstance(): MyMapsFragment = MyMapsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_my_maps, container, false)
        include = view.findViewById(R.id.mylocationdetailsinclude)
        searchButton = view.findViewById(R.id.fab)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        setLocation()
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        include.visibility = View.VISIBLE
        setAnimeView(requireContext(), include)
        ToastAlert(requireContext(), "ggjhgjhgjhgg jg jh")
        return true
    }

    private fun searchItem(){
        searchButton.setOnClickListener {
            loadPlacePicker()
        }
    }
    private fun loadPlacePicker() {
        val builder = PlacePicker.IntentBuilder()

        try {
            startActivityForResult(builder.build(requireActivity()), PLACE_PICKER_REQUEST)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(this, data)
                var addressText = place.name.toString()
                addressText += "\n" + place.address.toString()

                placeMarkerOnMap(place.latLng)
            }
        }
    }



    private fun setOnCClick(){
       // mapFragment.
    }

    */
}