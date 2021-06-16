package com.maisageis.ocorrencias.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.ui.action.SearchCepViewAction
import com.maisageis.ocorrencias.ui.login.LoginActivity
import com.maisageis.ocorrencias.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var loadPage: View

    private lateinit var txtNome: EditText
    private lateinit var txtCPF: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtNovaSenha: EditText
    private lateinit var txtConfirmacao: EditText
    private lateinit var txtCep: EditText
    private lateinit var txtLogradouro: EditText
    private lateinit var txtNumero: EditText
    private lateinit var txtComplemento: EditText
    private lateinit var txtBairro: EditText
    private lateinit var txtCidade: EditText
    private lateinit var txtUF: EditText

    private lateinit var btnBuscarCep: Button
    private lateinit var btnGravar: Button
    private lateinit var btnTerm: TextView
    private lateinit var btnLoginBack: TextView

    private lateinit var termsContract: CheckBox

    private val registerViewModel: RegisterViewModel by viewModel()

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        initSetOnClicks()
        setObservable()
    }

    private fun initSetOnClicks() {
        btnBuscarCep.setOnClickListener {
            registerViewModel.cepSearch(txtCep.text.toString())
        }

        btnGravar.setOnClickListener {
            if(validTerms()) {
                if (validInputs()) {
                    if (validPassword()) {
                        if(validCpf(txtCPF.text.toString())){
                            if(validEmail(txtEmail.text.toString()))
                                registerViewModel.register(createRequest())
                            else
                                ToastAlert(this@RegisterActivity, "Email Inválido")
                        }
                        else
                            ToastAlert(this@RegisterActivity, "CPF Inválido")
                    }
                    else
                        ToastAlert(this@RegisterActivity, "Senha deve conter pelo menos 6 digitos!")
                } else
                    ToastAlert(this@RegisterActivity, "Por favor, preenchar todos os campos")
            }
            else
                ToastAlert(this@RegisterActivity, "Necessário aceitar os termos")
        }

        btnTerm.setOnClickListener {
            startActivity(TermsActivity.newInstance(this))
        }

        btnLoginBack.setOnClickListener {
            startActivity(LoginActivity.newInstance(this))
            finish()
        }

        txtCPF.setOnFocusChangeListener(){ _: View, b: Boolean ->
            if(!b) {
                if(!validCpf(txtCPF.text.toString())){
                    ToastAlert(this, "CPF Inválido")
                }
            }
        }

        txtEmail.setOnFocusChangeListener(){ _: View, b: Boolean ->
            if(!b) {
                if(!validEmail(txtEmail.text.toString())){
                    ToastAlert(this, "Email Inválido")
                }
            }
        }
    }

    private fun validTerms(): Boolean = termsContract.isChecked

    private fun createRequest() = UserRequest(
        0, 0,
        txtCPF.text.toString(),
        txtNome.text.toString(),
        txtNome.text.toString(),
        "",
        txtEmail.text.toString(),
        txtNovaSenha.text.toString(),
        txtLogradouro.text.toString(),
        txtNumero.text.toString(),
        txtComplemento.text.toString(),
        txtBairro.text.toString(),
        txtCidade.text.toString(),
        txtUF.text.toString(),
        "",
        ""
    )

    private fun validPassword() = txtNovaSenha.text.toString() == txtConfirmacao.text.toString()

    private fun validInputs(): Boolean {
        return (
            !txtNome.text.isNullOrEmpty() &&
            !txtCPF.text.isNullOrEmpty() &&
            !txtEmail.text.isNullOrEmpty() &&
            !txtNovaSenha.text.isNullOrEmpty() &&
            !txtConfirmacao.text.isNullOrEmpty() &&
            !txtCep.text.isNullOrEmpty() &&
            !txtLogradouro.text.isNullOrEmpty() &&
            !txtNumero.text.isNullOrEmpty() &&
            !txtBairro.text.isNullOrEmpty() &&
            !txtNome.text.isNullOrEmpty() &&
            !txtBairro.text.isNullOrEmpty() &&
            !txtUF.text.isNullOrEmpty()
        )
    }

    private fun initViews() {
        loadPage = findViewById(R.id.registerProgressBar)

        txtNome = findViewById(R.id.edtNome)
        txtCPF = findViewById(R.id.edtCpf)
        txtEmail = findViewById(R.id.edtEmail)
        txtNovaSenha = findViewById(R.id.edtNovaSenha)
        txtConfirmacao = findViewById(R.id.edtConfirmacao)
        txtCep = findViewById(R.id.edtCep)
        txtLogradouro = findViewById(R.id.edtLogradouro)
        txtNumero = findViewById(R.id.edtNumero)
        txtComplemento = findViewById(R.id.edtComplemento)
        txtBairro = findViewById(R.id.edtBairro)
        txtCidade = findViewById(R.id.edCidade)
        txtUF = findViewById(R.id.edtUF)

        btnBuscarCep = findViewById(R.id.btnBuscarCep)
        btnGravar = findViewById(R.id.btnGravar)
        btnTerm = findViewById(R.id.lblContrato)
        btnLoginBack = findViewById(R.id.lblLogin)

        termsContract = findViewById(R.id.chkTermsContract)
    }

    private fun setObservable() {
        registerViewModel.actionCepSearchView.observe(this, Observer { state ->
            when(state){
                is SearchCepViewAction.Success -> this.successCepSearch(state.item)
                is SearchCepViewAction.Error -> this.errorCepSearch(state.item)
                is SearchCepViewAction.Loading -> loadingPage(state.loading)
            }
        })

        registerViewModel.actionView.observe(this, Observer { state ->
            when(state){
                is RegisterViewAction.Success -> this.success(state.item)
                is RegisterViewAction.Error -> this.error(state.item)
                is RegisterViewAction.Loading -> loadingPage(state.loading)
            }
        })
    }

    private fun success(value: UserResponse){
        ShowAlert(this@RegisterActivity, "Cadastro", "Cadastro efetuado com sucesso!!!",{
            startActivity(LoginActivity.newInstance(this))
            finish()
        }, "success")
        loadingPage(false)
    }

    private fun error(value: ErrorResponse){
        loadingPage(false)
        ToastAlert(this@RegisterActivity, "Falha ao efetuar o cadastro, tente novamente mais tarde!")
    }

    private fun successCepSearch(value: StreetCepResponse){
        txtLogradouro.setText(value.street)
        txtComplemento.setText(value.complement)
        txtBairro.setText(value.district)
        txtCidade.setText(value.city)
        txtUF.setText(value.state)
        loadingPage(false)
    }

    private fun errorCepSearch(value: ErrorResponse){
        loadingPage(false)
        ToastAlert(this@RegisterActivity, "Cep não encontrado...")
    }

    private fun loadingPage(visible: Boolean) {
        LoadPage(loadPage, getString(R.string.carregando), visible)
    }
}

