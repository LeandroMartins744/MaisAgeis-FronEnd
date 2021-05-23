package com.maisageis.ocorrencias.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.util.LoadPage

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var viewPage: View
    private lateinit var textView: TextView

    private lateinit var latitude: TextView
    private lateinit var longetude: TextView
    private lateinit var Search: Button
    private lateinit var result: TextView
    private lateinit var mainPage: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewPage = inflater.inflate(R.layout.main_fragment, container, false)
        return viewPage
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initOnClicks()
        loadingPage()
        mainPage.visibility = View.VISIBLE
    }

    private fun initViews() {
        latitude = viewPage.findViewById(R.id.txtLatitude)
        longetude = viewPage.findViewById(R.id.txtLongetide)
        Search = viewPage.findViewById(R.id.btnCoordenadas)
        result = viewPage.findViewById(R.id.txtResult)

        mainPage = viewPage.findViewById(R.id.main)
        mainPage.visibility = View.VISIBLE

        latitude.text = "-23.6822958"
        longetude.text = "-46.4421487"

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initOnClicks() {
        Search.setOnClickListener {
            viewModel.loadData(latitude.text.toString(), longetude.text.toString())
        }

        viewModel.data.observe(this, Observer {
            result.text = it.toString()
        })
    }

    private fun loadingPage() {
        viewModel.loadPage.observe(this, Observer {
            LoadPage(viewPage, getString(R.string.carregando), it)
        })
    }
}
