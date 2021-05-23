package com.maisageis.ocorrencias.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend.LegendPosition
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.CategoryResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.ui.login.LoginViewAction
import com.maisageis.ocorrencias.ui.login.LoginViewModel
import com.maisageis.ocorrencias.ui.slider.SliderActivity
import com.maisageis.ocorrencias.util.LoadPage
import com.maisageis.ocorrencias.util.ShowAlert
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var imageSlider: ImageSlider
    private lateinit var imageList: ArrayList<SlideModel>
    private lateinit var mChart: PieChart
    private lateinit var mView: View

    private lateinit var rvNomes: RecyclerView
    private lateinit var adapterNomes: AdapterCategoryHome

    private lateinit var loadPage: View

    private val homeViewModel: HomeViewModel by viewModel()

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)

        initViews()
        loadImageSlider()
        setObservable()
        initFunctions()
        setAdapter()

        return mView
    }

    private fun setAdapter() {

    }

    private fun initFunctions() {
        homeViewModel.getCategory()
    }

    private fun setObservable(){
        homeViewModel.actionCategoryView.observe(requireActivity(), androidx.lifecycle.Observer { state ->
            when(state) {
                is CategoryViewAction.Success -> this.successCategory(state.item)
                is CategoryViewAction.Error -> this.errorCategory(state.item)
                is CategoryViewAction.Loading -> loadingPage(state.loading)
            }
        })
    }

    private fun successCategory(category: List<CategoryResponse>) {
        adapterNomes = AdapterCategoryHome(
            requireContext(),
            category
        ) {item ->
            var tese = item

        }
        rvNomes.apply {
            adapter = adapterNomes
        }

        initGraphic(category)
    }

    private fun errorCategory(ex: ErrorResponse){
        loadingPage(false)
        //ShowAlert(this@LoginActivity, getString(R.string.loginInvalido))
    }

    private fun loadingPage(visible: Boolean) {
        //LoadPage(loadPage, getString(R.string.carregando), visible)
    }

    private fun loadImageSlider(){
        imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://bit.ly/2BteuF2"))
        imageList.add(SlideModel("https://bit.ly/3fLJf72"))

        imageSlider.setImageList(imageList)
    }

    private fun initGraphic(category: List<CategoryResponse>){
        mChart.contentDescription = ""
        mChart.centerText = generateCenterText()
        mChart.setCenterTextSize(10f)
        mChart.holeRadius = 45f
        mChart.transparentCircleRadius = 50f

        val l = mChart.legend
        l.position = LegendPosition.RIGHT_OF_CHART

        mChart.data = generatePieData(category)
        mChart.notifyDataSetChanged()
    }

    private fun initViews() {
        imageSlider = mView.findViewById(R.id.image_slider)
        mChart = mView.findViewById(R.id.chart1)
        rvNomes = mView.findViewById(R.id.recycleHome)
        rvNomes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
    }

    private fun generateCenterText(): SpannableString? {
        val s = SpannableString("Ocorrências \ndo mês proximo a você")
        s.setSpan(RelativeSizeSpan(2f), 0, 11, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 11, s.length, 0)
        return s
    }

    protected fun generatePieData(category: List<CategoryResponse>): PieData? {
        val entries1 = ArrayList<Entry>()
        val xVals = ArrayList<String>()
        for (item in category)
            xVals.add(item.name)

        val count = xVals.size

        for (i in 0 until count) {
            xVals.add("entry" + (i + 1))
            entries1.add(
                Entry(category[i].total.toFloat() + 40, i )
            )
//            entries1.add(
//                Entry((Math.random() * 60).toFloat() + 40, i )
//            )
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
    protected fun generatePieData1(category: List<CategoryResponse>): PieData? {
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
