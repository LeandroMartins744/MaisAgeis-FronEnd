package com.maisageis.ocorrencias.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.textview.MaterialTextView
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.LoginRequest
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.ui.register.RegisterActivity
import com.maisageis.ocorrencias.ui.register.RegisterViewModel
import com.maisageis.ocorrencias.ui.slider.SliderActivity
import com.maisageis.ocorrencias.util.LoadPage
import com.maisageis.ocorrencias.util.SecurityData
import com.maisageis.ocorrencias.util.SendPassword
import com.maisageis.ocorrencias.util.ShowAlert
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var lxlInsert: MaterialTextView
    private lateinit var lxlEsqueci: MaterialTextView
    private lateinit var txtLogin: TextView
    private lateinit var txtPassword: TextView
    private lateinit var btnLogin: Button
    private lateinit var loadPage: View

    private val loginViewModel: LoginViewModel by viewModel()
    private val securityData: SecurityData by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initOnClicks()
        setObservable()
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
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
        lxlInsert.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        lxlEsqueci.setOnClickListener {
            ShowAlert(this@LoginActivity, "Login Usuário", getString(R.string.loginInvalido), {}, "success")
            ShowAlert(this@LoginActivity, "Login Usuário", getString(R.string.loginInvalido), {}, "")
            ShowAlert(this, "teste", "ghj hjghjghjg h bn vv dfgdf g b  df bm, bfd m,b g fgfdgdfgvdffg d dgdfdf", {}, "success")
            SendPassword(this)
        }

        btnLogin.setOnClickListener {
            loginViewModel.loginUser(LoginRequest(txtLogin.text.toString(), txtPassword.text.toString()))
        }
    }

    private fun successLogin(user: UserResponse) {
        securityData.setUser(user)
        val intent = Intent(this@LoginActivity, SliderActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun errorLogin(ex: ErrorResponse){
        loadingPage(false)
        if(ex.code == 408)
            ShowAlert(this@LoginActivity, "Login Usuário", getString(R.string.logindesativado), {}, "error")
        else
            ShowAlert(this@LoginActivity, "Login Usuário", getString(R.string.loginInvalido), {}, "error")
    }

    private fun initViews() {
        loadPage = findViewById(R.id.loginProgressBar)
        lxlEsqueci = findViewById(R.id.lblEsqueciSenha)
        lxlInsert = findViewById(R.id.lblCadastro)
        txtLogin = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtSenha)
        btnLogin = findViewById(R.id.btnLoginLogar)
    }

    private fun loadingPage(visible: Boolean) {
        LoadPage(loadPage, getString(R.string.carregando), visible)
    }
}