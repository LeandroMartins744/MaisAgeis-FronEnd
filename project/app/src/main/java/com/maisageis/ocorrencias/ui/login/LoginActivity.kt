    package com.maisageis.ocorrencias.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textview.MaterialTextView
import com.maisageis.ocorrencias.MainActivity
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.cadastro.CadastroActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

    class LoginActivity : AppCompatActivity() {

    private lateinit var labelCadastro: MaterialTextView
    private lateinit var txtLogin: TextView
    private lateinit var txtSenha: TextView
    private lateinit var btnLogin: Button
    private lateinit var loadPage: View

    private var loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initOnClicks()
        loadingPage()
       // setObservable()
        //loginViewModel.loginUser("teste@teste.com", "123456")
    }

   /* private fun setObservable() =
        loginViewModel.actionView.observe(this, Observer { state ->
            when(state){
                is LoginViewAction.Success ->{ }
                is LoginViewAction.Error -> { }
                is LoginViewAction.Loading -> { }
            }
        })

    */

    private fun initOnClicks() {
        labelCadastro.setOnClickListener {
            startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
        }

      /*  loginViewModel.user.observe(this, Observer {
            if (it == null)
                ShowAlert(this@LoginActivity, getString(R.string.loginInvalido))
            else {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })*/

        btnLogin.setOnClickListener {
          //  loginViewModel.loadData(txtLogin.text.toString(), txtSenha.text.toString())
        }
    }

    private fun initViews() {
        loadPage = findViewById(R.id.loginProgressBar)
        labelCadastro = findViewById(R.id.lblCadastro)
        txtLogin = findViewById(R.id.txtEmail)
        txtSenha = findViewById(R.id.txtSenha)
        btnLogin = findViewById(R.id.btnLoginLogar)

      //  loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun loadingPage() {
        //loginViewModel.loadPage.observe(this, Observer {
       //     LoadPage(loadPage, getString(R.string.carregando), it)
        //})
    }
}