package com.maisageis.ocorrencias.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import com.facebook.shimmer.ShimmerFrameLayout
import com.maisageis.ocorrencias.R

fun ToastAlert(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

private fun setAnimeView(context: Context, view: View, animation: Int = R.anim.fadein){
    var animFadeIn: Animation = AnimationUtils.loadAnimation(
        context,
        animation
    )
    view.startAnimation(animFadeIn)
}

fun ShowAlert(context: Context, titleDialog: String, messageDialog: String, onClick: (String) -> Unit, icon: String = ""){

    val messageBoxView = LayoutInflater.from(context).inflate(R.layout.custom_message, null)
    val messageBoxBuilder = AlertDialog.Builder(context).setView(messageBoxView)

    messageBoxBuilder.setCancelable(false)

    var title: TextView = messageBoxView.findViewById(R.id.txtPassTitle)
    var image: ImageView = messageBoxView.findViewById(R.id.imgMsgAlert)
    var message: TextView = messageBoxView.findViewById(R.id.txtMsgMessage)
    var button: Button = messageBoxView.findViewById(R.id.btnMsgOk)

    title.text = titleDialog
    message.text = messageDialog
    when (icon) {
        "" -> image.visibility = View.GONE
        "success" -> image.setImageResource(R.drawable.ic_alert_success)
        else -> image.setImageResource(R.drawable.ic_alert_error)
    }

    val  messageBoxInstance = messageBoxBuilder.show()
    button.setOnClickListener {
        messageBoxInstance.dismiss()
        onClick.invoke("")
    }

    setAnimeView(context, image)
    setAnimeView(context, message)
}

fun SendPassword(context: Context){
    val messageBoxView = LayoutInflater.from(context).inflate(R.layout.custom_password, null)
    val messageBoxBuilder = AlertDialog.Builder(context).setView(messageBoxView)
    messageBoxBuilder.setCancelable(false)

    var title: TextView = messageBoxView.findViewById(R.id.txtPassTitle)
    var email: TextView = messageBoxView.findViewById(R.id.txtPassOld)
    var cancel: Button = messageBoxView.findViewById(R.id.btnDialCancel)
    var send: Button = messageBoxView.findViewById(R.id.btnDialSend)

    title.text = "Esqueci a senha"
    val  messageBoxInstance = messageBoxBuilder.show()

    cancel.setOnClickListener {
        messageBoxInstance.dismiss()
    }

    send.setOnClickListener {
        if(email.text.isNotEmpty()){
            messageBoxInstance.dismiss()
        }
        else
            Toast.makeText(context, "Email precisa ser preenchido", Toast.LENGTH_LONG).show()
    }

    messageBoxView.setOnClickListener(){
        messageBoxInstance.dismiss()
    }

    setAnimeView(context, email)
    setAnimeView(context, cancel)
    setAnimeView(context, send)
}

fun SendUpdatePassword(context: Context){
    val messageBoxView = LayoutInflater.from(context).inflate(R.layout.custom_alter_password, null)
    val messageBoxBuilder = AlertDialog.Builder(context).setView(messageBoxView)
    messageBoxBuilder.setCancelable(false)

    var title: TextView = messageBoxView.findViewById(R.id.txtPassTitle)
    var ord: TextView = messageBoxView.findViewById(R.id.txtPassOld)
    var new: TextView = messageBoxView.findViewById(R.id.txtPassNew)
    var conf: TextView = messageBoxView.findViewById(R.id.txtPassConf)
    var cancel: Button = messageBoxView.findViewById(R.id.btnDialCancel)
    var send: Button = messageBoxView.findViewById(R.id.btnDialSend)

    title.text = "Esqueci a senha"
    val  messageBoxInstance = messageBoxBuilder.show()

    cancel.setOnClickListener {
        messageBoxInstance.dismiss()
    }

    send.setOnClickListener {
        if(ord.text.isNotEmpty()){
            messageBoxInstance.dismiss()
        }
        else
            Toast.makeText(context, "Email precisa ser preenchido", Toast.LENGTH_LONG).show()
    }

    messageBoxView.setOnClickListener(){
        messageBoxInstance.dismiss()
    }

    setAnimeView(context, ord)
    setAnimeView(context, new)
    setAnimeView(context, conf)
    setAnimeView(context, cancel)
    setAnimeView(context, send)
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

fun LoadShimmer(shimmer: ShimmerFrameLayout, visible: Boolean = false, viewLayout: View? = null){
    if(visible) {
        shimmer.startShimmerAnimation()
        shimmer.visibility = View.VISIBLE
        viewLayout?.visibility = View.GONE
    }
    else {
        shimmer.stopShimmerAnimation()
        shimmer.visibility = View.GONE
        viewLayout?.visibility = View.VISIBLE
    }
}