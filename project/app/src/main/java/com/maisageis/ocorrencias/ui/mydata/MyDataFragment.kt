package com.maisageis.ocorrencias.ui.mydata

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.LoginResponse
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.StreetResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.ui.action.SearchCepViewAction
import com.maisageis.ocorrencias.ui.login.LoginActivity
import com.maisageis.ocorrencias.util.*
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import java.io.FileNotFoundException
import java.lang.ref.WeakReference


class MyDataFragment : Fragment() {

    private lateinit var imgMDImage: ImageView
    private lateinit var txtMDName: EditText
    private lateinit var txtMDApelido: EditText
    private lateinit var txtMDCpf: EditText
    private lateinit var txtMDCep: EditText
    private lateinit var txtMDStreet: EditText
    private lateinit var txtMDNumber: EditText
    private lateinit var txtMDComplement: EditText
    private lateinit var txtMDDistintic: EditText
    private lateinit var txtMDCity: EditText
    private lateinit var txtMDState: EditText
    private lateinit var btnMDAlter: Button
    private lateinit var btnMDAlterPasssword: Button
    private lateinit var btnMDSearchCep: Button
    private lateinit var btnMDLogoff: Button

    private lateinit var viewItem: View
    private val securityData: SecurityData by inject()
    private val myDataViewModel: MyDataViewModel by inject()

    private lateinit var userData: UserResponse

    private val IMAGE_GALLERY_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewItem = view
        initViews()
        initData()
        setOnClicks()
        setObservable()
    }

    private fun setObservable() {
        myDataViewModel.actionCepSearchView.observe(requireActivity(), Observer { state ->
            when(state){
                is SearchCepViewAction.Success -> this.successCepSearch(state.item)
                is SearchCepViewAction.Error -> this.errorCepSearch(state.item)
                is SearchCepViewAction.Loading -> loadingPage(state.loading)
            }
        })

        myDataViewModel.actionUserView.observe(requireActivity(), Observer { state ->
            when(state){
                is UserActionView.Success -> this.success(state.item)
                is UserActionView.Error -> this.error(state.item)
                is UserActionView.Loading -> loadingPage(state.loading)
            }
        })
    }
    private fun success(value: Boolean){
        loadingPage(false)
        securityData.setUser(createResponse())
        ShowAlert(requireContext(), "Alterar Usuário", "Usuário alterado com sucesso", {}, "success")
    }

    private fun error(value: ErrorResponse){
        loadingPage(false)
        ShowAlert(requireContext(), "Alterar Usuário", "Falha ao alterar usuário", {}, "error")
    }

    private fun successCepSearch(value: StreetCepResponse){
        txtMDStreet.setText(value.street)
        txtMDComplement.setText(value.complement)
        txtMDDistintic.setText(value.district)
        txtMDCity.setText(value.city)
        txtMDState.setText(value.state)
        loadingPage(false)
    }

    private fun errorCepSearch(value: ErrorResponse){
        loadingPage(false)
        ToastAlert(requireContext(), "Cep não encontrado...")
    }

    private fun loadingPage(visible: Boolean) {
        //LoadPage(loadPage, getString(R.string.carregando), visible)
    }

    private fun setOnClicks() {
        btnMDAlterPasssword.setOnClickListener {
            SendUpdatePassword(requireActivity(), userData.login)
        }

        btnMDAlter.setOnClickListener {
            if (validInputs()) {
                loadingPage(true)
                myDataViewModel.alterUser(createRequest())
            } else
                ToastAlert(requireContext(), "Por favor, preenchar todos os campos")
        }

        imgMDImage.setOnClickListener {
            selectImageClick()
        }

        btnMDLogoff.setOnClickListener {
            securityData.deleteValue()
            ShowAlert(requireContext(), "Sair App", getString(R.string.closeapp), {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }, "success")
        }

        btnMDSearchCep.setOnClickListener {
            myDataViewModel.cepSearch(txtMDCep.text.toString())
        }
    }

    private fun createRequest() = UserRequest(
        userData.id,
        userData.street.idStreet,
        userData.cpf,
        txtMDName.text.toString(),
        txtMDApelido.text.toString(),
        "",
        userData.login.user,
        "",
        txtMDStreet.text.toString(),
        txtMDNumber.text.toString(),
        txtMDComplement.text.toString(),
        txtMDDistintic.text.toString(),
        txtMDCity.text.toString(),
        txtMDState.text.toString(),
        "",
        ""
    )
    private fun createResponse() = UserResponse(
        id = userData.id,
        street = StreetResponse(
            idStreet =  userData.street.idStreet,
            street = txtMDStreet.text.toString(),
            number = txtMDNumber.text.toString(),
            complement = txtMDComplement.text.toString(),
            district = txtMDDistintic.text.toString(),
            city = txtMDCity.text.toString(),
            state = txtMDState.text.toString(),
            longetude = "",
            latitude = ""
        ),
        login = userData.login,
        log = userData.log,
        cpf = userData.cpf,
        name = txtMDName.text.toString(),
        surname = txtMDApelido.text.toString(),
        image = ""
    )

    private fun validInputs(): Boolean {
        return (
                !txtMDName.text.isNullOrEmpty() &&
                !txtMDApelido.text.isNullOrEmpty() &&
                !txtMDStreet.text.isNullOrEmpty() &&
                !txtMDNumber.text.isNullOrEmpty() &&
                !txtMDComplement.text.isNullOrEmpty() &&
                !txtMDDistintic.text.isNullOrEmpty() &&
                !txtMDCity.text.isNullOrEmpty() &&
                !txtMDState.text.isNullOrEmpty()
        )
    }

    private fun initData() {
        userData = securityData.getUer()!!

        txtMDName.setText(userData.name)
        txtMDApelido.setText(userData.surname)
        txtMDCpf.setText(userData.cpf)
        txtMDCep.setText("")
        txtMDStreet.setText(userData.street.street)
        txtMDNumber.setText(userData.street.number)
        txtMDComplement.setText(userData.street.complement)
        txtMDDistintic.setText(userData.street.district)
        txtMDCity.setText(userData.street.city)
        txtMDState.setText(userData.street.state)
    }

    private fun initViews() {
        imgMDImage = viewItem.findViewById(R.id.imgMDImage)
        txtMDName = viewItem.findViewById(R.id.txtMDName)
        txtMDApelido = viewItem.findViewById(R.id.txtMDApelido)
        txtMDCpf = viewItem.findViewById(R.id.txtMDCpf)
        txtMDCep = viewItem.findViewById(R.id.txtMDCep)
        txtMDStreet = viewItem.findViewById(R.id.txtMDStreet)
        txtMDNumber = viewItem.findViewById(R.id.txtMDNumber)
        txtMDComplement = viewItem.findViewById(R.id.txtMDComplement)
        txtMDDistintic = viewItem.findViewById(R.id.txtMDDistintic)
        txtMDCity = viewItem.findViewById(R.id.txtMDCity)
        txtMDState = viewItem.findViewById(R.id.txtMDState)
        btnMDAlter = viewItem.findViewById(R.id.btnMDAlter)
        btnMDAlterPasssword = viewItem.findViewById(R.id.btnMDAlterPasssword)
        btnMDSearchCep = viewItem.findViewById(R.id.btnMDSearchCep)
        btnMDLogoff = viewItem.findViewById(R.id.btnMDLogoff)
    }

    companion object {
        fun newInstance(): MyDataFragment = MyDataFragment()
    }

    fun selectImageClick() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Selecionar IMagem"
                ),
                IMAGE_GALLERY_REQUEST
            )
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                IMAGE_GALLERY_REQUEST
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_GALLERY_REQUEST && resultCode == RESULT_OK) {
            var imgSel = data?.data
            Picasso.get().load(data?.data.toString()).into(imgMDImage)
        }
    }
}
