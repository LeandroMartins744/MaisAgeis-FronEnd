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
import com.maisageis.ocorrencias.model.response.LoginResponse

fun ToastAlert(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun setAnimeView(context: Context, view: View, animation: Int = R.anim.fadein){
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

fun SendPassword(context: Context, onClick: (String) -> Unit){
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
            onClick.invoke(email.text.toString())
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

fun SendUpdatePassword(context: Context, login: LoginResponse){
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
        if(ord.text.toString() == login.password)
        {
            if(new.length() > 5){
                if(new.text.toString() == conf.text.toString()){
                    Toast.makeText(context, "Senha alterada com sucesso", Toast.LENGTH_LONG).show()
                    messageBoxInstance.dismiss()
                }
                else
                    Toast.makeText(context, "Senha diferente de confirmação", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(context, "Senha precisar ter 6 digitos", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(context, "Senha antiga inválida", Toast.LENGTH_LONG).show()

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

fun validCpf(cpf: String): Boolean {
    if (cpf.isEmpty()) return false

    val numbers = arrayListOf<Int>()

    cpf.filter { it.isDigit() }.forEach {
        numbers.add(it.toString().toInt())
    }

    if (numbers.size != 11) return false

    (0..9).forEach { n ->
        val digits = arrayListOf<Int>()
        (0..10).forEach { digits.add(n) }
        if (numbers == digits) return false
    }

    val dv1 = ((0..8).sumBy { (it + 1) * numbers[it] }).rem(11).let {
        if (it >= 10) 0 else it
    }

    val dv2 = ((0..8).sumBy { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
        if (it >= 10) 0 else it
    }

    return numbers[9] == dv1 && numbers[10] == dv2
}

fun validEmail(email:String): Boolean{
    return if (email.isEmpty()) {
        false;
    } else {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}