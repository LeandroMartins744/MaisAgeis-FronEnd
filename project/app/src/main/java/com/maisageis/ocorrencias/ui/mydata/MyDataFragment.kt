package com.maisageis.ocorrencias.ui.mydata

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.util.SecurityData
import com.maisageis.ocorrencias.util.SendUpdatePassword
import org.koin.android.ext.android.inject
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

    private lateinit var viewItem: View
    private val securityData: SecurityData by inject()

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
    }

    private fun setOnClicks() {
        btnMDAlterPasssword.setOnClickListener {
            SendUpdatePassword(requireActivity())
        }

        imgMDImage.setOnClickListener {
            val outputFileOptions = ImageCapture.OutputFileOptions.Builder(File(...)).build()
            imageCapture.takePicture(outputFileOptions, cameraExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(error: ImageCaptureException)
                    {
                        // insert your code here.
                    }
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        // insert your code here.
                    }
                })
        }
    }

    private fun initData() {
        userData = securityData.getUer()!!

        txtMDName.setText(userData.name)
        txtMDApelido.setText(userData.surname)
        txtMDCpf.setText(userData.cpf)
        txtMDCep.setText("00000-00")
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
            ///LoadImageTask(this).execute(data.data)
        }
    }
}
