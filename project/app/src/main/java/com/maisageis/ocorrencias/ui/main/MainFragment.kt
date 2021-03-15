package com.maisageis.ocorrencias.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.repository.LoginRepository

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var viewPage: View
    private lateinit var textView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewPage = inflater.inflate(R.layout.main_fragment, container, false)
        return viewPage
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,
                MainViewModel.MainViewModelFactory(LoginRepository())
        ).get(MainViewModel::class.java)

        textView = viewPage.rootView.findViewById(R.id.message)
        textView.text = "Carregando"

        viewModel.listUsers.observe(viewLifecycleOwner, Observer {
            textView.text = it.first().login
        })

        viewModel.getUsers()
    }

}