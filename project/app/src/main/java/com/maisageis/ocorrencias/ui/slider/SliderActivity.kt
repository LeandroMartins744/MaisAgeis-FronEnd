package com.maisageis.ocorrencias.ui.slider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maisageis.ocorrencias.MainActivity
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.login.LoginActivity

class SliderActivity : AppCompatActivity() {

    private lateinit var imageSlider: ImageSlider
    private lateinit var closeSlider: FloatingActionButton
    private lateinit var imageList: ArrayList<SlideModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)

        initViews()
        initSetClicks()
        loadImageSlider()

        imageSlider.setImageList(imageList)
    }

    private fun loadImageSlider(){
        imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://bit.ly/2YoJ77H"))
        imageList.add(SlideModel("https://bit.ly/2BteuF2"))
        imageList.add(SlideModel("https://bit.ly/3fLJf72", "And people do that."))
    }

    private fun initSetClicks() {
        closeSlider.setOnClickListener {
            val intent = Intent(this@SliderActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initViews() {
        imageSlider = findViewById(R.id.image_slider)
        closeSlider = findViewById(R.id.closeSlider)
    }
}