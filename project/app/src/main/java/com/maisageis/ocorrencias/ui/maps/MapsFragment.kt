package com.maisageis.ocorrencias.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.ui.action.CategoryViewAction
import com.maisageis.ocorrencias.ui.action.SearchCepViewAction
import com.maisageis.ocorrencias.ui.detail.DetailFragment
import com.maisageis.ocorrencias.ui.register.RegisterViewModel
import com.maisageis.ocorrencias.util.LoadPage
import com.maisageis.ocorrencias.util.ToastAlert
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapsFragment : Fragment() {

    private lateinit var cep: TextView
    private lateinit var street: TextView
    private lateinit var district: TextView
    private lateinit var city: TextView
    private lateinit var state: TextView
    private lateinit var searchCep: Button
    private lateinit var search: Button

    private lateinit var loadPage: View
    private lateinit var mView: View
    private val mapsViewModel: MapsViewModel by viewModel()

    companion object {
        fun newInstance(): MapsFragment = MapsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_maps, container, false)

        initViews()
        setOnClick()
        setObservable()

        return mView
    }

    private fun setOnClick() {
        searchCep.setOnClickListener {
            mapsViewModel.cepSearch(this.cep.text.toString())
        }

        search.setOnClickListener {
            var bundle: Bundle = Bundle()
            bundle.putString("category", "2")
            val details = DetailFragment.newInstance()
            details.arguments = bundle

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, details)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun initViews() {
        this.loadPage = mView.findViewById(R.id.mapsProgressBar)
        this.cep = mView.findViewById(R.id.MapstxtCep)
        this.street = mView.findViewById(R.id.MapstxtStreet)
        this.district = mView.findViewById(R.id.MapstxtDistricty)
        this.city = mView.findViewById(R.id.MapstxtCity)
        this.state = mView.findViewById(R.id.MapstxtState)
        this.searchCep = mView.findViewById(R.id.MapsSearchCep)
        this.search = mView.findViewById(R.id.MapsbtnSearch)
    }

    private fun setObservable(){
        mapsViewModel.actionCepSearchView.observe(requireActivity(), Observer { state ->
            when(state){
                is SearchCepViewAction.Success -> this.successCepSearch(state.item)
                is SearchCepViewAction.Error -> this.errorCepSearch(state.item)
                is SearchCepViewAction.Loading -> loadingPage(state.loading)
            }
        })
    }

    private fun successCepSearch(value: StreetCepResponse){
        this.street.text = value.street
        this.district.text = value.district
        this.city.text = value.city
        this.state.text = value.state
        loadingPage(false)
    }

    private fun errorCepSearch(value: ErrorResponse){
        loadingPage(false)
        ToastAlert(requireContext(), "Cep não encontrado...")
    }

    private fun loadingPage(visible: Boolean) {
        LoadPage(loadPage, getString(R.string.carregando), visible)
    }
}