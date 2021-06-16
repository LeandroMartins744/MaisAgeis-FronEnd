package com.maisageis.ocorrencias.ui.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.maps.MapsFragment
import com.maisageis.ocorrencias.ui.maps.MapsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private lateinit var mView: View


    private lateinit var lvContatos: ListView
    private lateinit var contatos: ArrayList<String>
    private lateinit var adapterContatos: ArrayAdapter<String>

    private val mapsViewModel: MapsViewModel by viewModel()

    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun iniVars() {
        lvContatos = mView.findViewById(R.id.lvContatos)
        gerarNomes(20)
        adapterContatos = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            contatos
        )

        lvContatos.adapter = adapterContatos
    }

    private fun gerarNomes(quantidade: Int) {
        contatos = arrayListOf()

        for( i in 1..quantidade) {
            contatos.add("Nome - $i")
        }

    }

    private fun iniActions() {
        lvContatos.setOnItemClickListener { parent, _, position, _ ->
            val nome = parent.getItemAtPosition(position) as String

           // (targetFragment as? MapsFragment)?.setName(nome)
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_list, container, false)
        iniVars()
        iniActions()
        return mView
    }

    private fun setClicks(){
        //var button : Button = mView.findViewById(R.id.btnBackTest)
       // button.setOnClickListener {
       //    requireActivity().onBackPressedDispatcher.addCallback(callback)
       // }
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //Navigation.findNavController(view).navigate(R.id.action_patientInfoFragment_to_patientsList)
        }
    }

}