package com.maisageis.ocorrencias.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.repository.LoginRepository

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,
                MainViewModel.MainViewModelFactory(LoginRepository())
        ).get(MainViewModel::class.java)

        viewModel.listUsers.observe(viewLifecycleOwner, Observer {
            var teste = it
        })
    }

}