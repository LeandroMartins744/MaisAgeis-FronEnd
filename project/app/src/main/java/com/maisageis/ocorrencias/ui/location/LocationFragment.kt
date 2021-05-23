package com.maisageis.ocorrencias.ui.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.mydata.MyDataFragment

class LocationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    companion object {
        fun newInstance(): LocationFragment = LocationFragment()
    }
}