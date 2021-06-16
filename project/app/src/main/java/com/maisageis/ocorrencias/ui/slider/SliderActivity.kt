package com.maisageis.ocorrencias.ui.slider

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maisageis.ocorrencias.MainActivity
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.login.LoginActivity

class SliderActivity : AppCompatActivity() {

    private lateinit var imageSlider: ImageSlider
    private lateinit var closeSlider: ImageView
    private lateinit var imageList: ArrayList<SlideModel>

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, SliderActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)

        initViews()
        initSetClicks()
        loadImageSlider()

        imageSlider.setImageList(imageList)
    }

    private fun loadImageSlider(){
        imageList = ArrayList()

        imageList.add(SlideModel(R.drawable.banner01, scaleType = ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.banner02, scaleType = ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.banner03, scaleType = ScaleTypes.CENTER_CROP))
    }

    private fun initSetClicks() {
        closeSlider.setOnClickListener {
            startActivity(MainActivity.newInstance(this))
            finish()
        }
    }

    private fun initViews() {
        imageSlider = findViewById(R.id.image_slider)
        closeSlider = findViewById(R.id.closeSlider)
        //closeSlider.visibility = View.GONE
    }
}