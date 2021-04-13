package com.maisageis.ocorrencias.util

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.maisageis.ocorrencias.R

fun ShowAlert(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun LoadPage(view: View, text: String, visible: Boolean = false){
    if(visible) {
        var textload: TextView = view.rootView.findViewById(R.id.loadText)
        textload.text = text
        view.visibility = View.VISIBLE
    }
    else
        view.visibility = View.GONE
}