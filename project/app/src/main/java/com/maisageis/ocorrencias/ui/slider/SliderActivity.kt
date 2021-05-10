package com.maisageis.ocorrencias.ui.slider

import android.content.Context
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

        imageList.add(SlideModel("https://bit.ly/2YoJ77H"))
        imageList.add(SlideModel("https://bit.ly/2BteuF2"))
        imageList.add(SlideModel("https://bit.ly/3fLJf72", "And people do that."))
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
    }
}