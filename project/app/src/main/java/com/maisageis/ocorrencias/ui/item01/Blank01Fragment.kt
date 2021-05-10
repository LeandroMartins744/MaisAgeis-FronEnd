package com.maisageis.ocorrencias.ui.item01

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend.LegendPosition
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maisageis.ocorrencias.R
import java.util.*


class Blank01Fragment : Fragment() {

    private lateinit var imageSlider: ImageSlider
    private lateinit var imageList: ArrayList<SlideModel>
    private lateinit var mChart: PieChart
    private lateinit var mView: View

    companion object {
        fun newInstance(): Blank01Fragment = Blank01Fragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_blank01, container, false)

        initViews()
        loadImageSlider()
        initGraphic()

        return mView
    }

    private fun loadImageSlider(){
        imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://bit.ly/2BteuF2"))
        imageList.add(SlideModel("https://bit.ly/3fLJf72"))

        imageSlider.setImageList(imageList)
    }

    private fun initGraphic(){

        mChart.contentDescription = ""
        //var tf = Typeface.createFromAsset(context!!.assets, "OpenSans-Light.ttf")
       // mChart.setCenterTextTypeface(tf)

        mChart.centerText = generateCenterText()
        mChart.setCenterTextSize(10f)
        //mChart.setCenterTextTypeface(tf)

        mChart.holeRadius = 45f
        mChart.transparentCircleRadius = 50f

        val l = mChart.legend
        l.position = LegendPosition.RIGHT_OF_CHART

        mChart.data = generatePieData()
    }

    private fun initViews() {
        imageSlider = mView.findViewById(R.id.image_slider)
        mChart = mView.findViewById(R.id.chart1)
    }

    private fun generateCenterText(): SpannableString? {
        val s = SpannableString("Ocorrências \ndo mês proximo a você")
        s.setSpan(RelativeSizeSpan(2f), 0, 11, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 11, s.length, 0)
        return s
    }

    protected fun generatePieData(): PieData? {
        val entries1 = ArrayList<Entry>()
        val xVals = ArrayList<String>()
        xVals.add("HOMIC. DOLOSO")
        xVals.add("FEMINIÇIDIO")
        xVals.add("LATROCÍNIO")
        xVals.add("LESÃO CORPORAL")
        xVals.add("FURTO/ROUBO")

        val count = xVals.size

        for (i in 0 until count) {
            xVals.add("entry" + (i + 1))
            entries1.add(
                Entry((Math.random() * 60).toFloat() + 40, i )
            )
        }
        val ds1 = PieDataSet(entries1, "Quarterly Revenues 2015")
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS)
        ds1.sliceSpace = 2f
        ds1.valueTextColor = Color.WHITE
        ds1.valueTextSize = 12f
        val d = PieData(xVals, ds1)
       // d.setValueTypeface(tf)
        return d
    }
}