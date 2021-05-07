package com.maisageis.ocorrencias.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.textview.MaterialTextView
import com.maisageis.ocorrencias.MainActivity
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.UserModel
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.ui.cadastro.CadastroActivity
import com.maisageis.ocorrencias.util.LoadPage
import com.maisageis.ocorrencias.util.ShowAlert
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var labelCadastro: MaterialTextView
    private lateinit var txtLogin: TextView
    private lateinit var txtSenha: TextView
    private lateinit var btnLogin: Button
    private lateinit var loadPage: View

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initOnClicks()
        setObservable()
    }

     private fun setObservable() =
         loginViewModel.actionView.observe(this, Observer { state ->
             when(state){
                 is LoginViewAction.Success -> this.successLogin(state.item)
                 is LoginViewAction.Error -> this.errorLogin(state.item)
                 is LoginViewAction.Loading -> loadingPage(state.loading)
             }
         })

    private fun initOnClicks() {
        labelCadastro.setOnClickListener {
            startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
        }

        btnLogin.setOnClickListener {
            loginViewModel.loginUser(txtLogin.text.toString(), txtSenha.text.toString())
        }
    }

    private fun successLogin(user: UserResponse) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun errorLogin(ex: ErrorResponse){
        loadingPage(false)
        ShowAlert(this@LoginActivity, getString(R.string.loginInvalido))
    }

    private fun initViews() {
        loadPage = findViewById(R.id.loginProgressBar)
        labelCadastro = findViewById(R.id.lblCadastro)
        txtLogin = findViewById(R.id.txtEmail)
        txtSenha = findViewById(R.id.txtSenha)
        btnLogin = findViewById(R.id.btnLoginLogar)
    }

    private fun loadingPage(visible: Boolean) {
        LoadPage(loadPage, getString(R.string.carregando), visible)
    }
}