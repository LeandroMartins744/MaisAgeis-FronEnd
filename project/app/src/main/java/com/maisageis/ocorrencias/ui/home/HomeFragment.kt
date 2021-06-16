package com.maisageis.ocorrencias.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend.LegendPosition
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.CategoryResponse
import com.maisageis.ocorrencias.ui.action.CategoryViewAction
import com.maisageis.ocorrencias.ui.detail.DetailFragment
import com.maisageis.ocorrencias.util.LoadShimmer
import com.maisageis.ocorrencias.util.LocationStatus
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var imageSlider: ImageSlider
    private lateinit var imageList: ArrayList<SlideModel>
    private lateinit var mChart: PieChart
    private lateinit var mView: View
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var layoutView: NestedScrollView

    private lateinit var rvNomes: RecyclerView
    private lateinit var adapterNomes: AdapterCategoryHome

    private val homeViewModel: HomeViewModel by viewModel()
    private val locationStatus: LocationStatus by inject()

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
        setLocation()

        return mView
    }

    @SuppressLint("MissingPermission")
    private fun setLocation() {
       // locationStatus.configurationService {
            //Toast.makeText(requireContext(), it.latitude.toString() + " --- Long:" + it.longitude.toString(), Toast.LENGTH_LONG).show()
            //locationStatus.stopService()
       // }
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
        )
        {
            var bundle: Bundle = Bundle()
            bundle.putString("category", it.id.toString())
            val details = DetailFragment.newInstance()
            details.arguments = bundle

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, details)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        rvNomes.apply {
            adapter = adapterNomes
        }

        initGraphic(category)
        loadingPage(false)
    }

    private fun errorCategory(ex: ErrorResponse){
        loadingPage(false)
    }

    private fun loadingPage(visible: Boolean) {
        LoadShimmer(shimmer, visible,layoutView)
    }

    private fun loadImageSlider(){
        imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.slider01, scaleType = ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.slider02, scaleType = ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.slider03, scaleType = ScaleTypes.CENTER_CROP))

        imageSlider.setImageList(imageList)
    }

    private fun initGraphic(category: List<CategoryResponse>){
        mChart.contentDescription = ""
        mChart.setDescription("")
        mChart.centerText = generateCenterText()
        mChart.setCenterTextSize(10f)
        mChart.holeRadius = 45f
        mChart.transparentCircleRadius = 50f

        val l = mChart.legend
        l.position = LegendPosition.RIGHT_OF_CHART

        mChart.setDrawSliceText(false)
        mChart.setDrawSlicesUnderHole(false)

        mChart.data = generatePieData(category)
        mChart.invalidate()
    }

    private fun initViews() {
        shimmer = mView.findViewById(R.id.shimmer_home)
        imageSlider = mView.findViewById(R.id.image_slider)
        mChart = mView.findViewById(R.id.chart1)
        layoutView = mView.findViewById(R.id.layout_home)
        rvNomes = mView.findViewById(R.id.recycleHome)
        rvNomes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)

        loadingPage(true)
    }

    private fun generateCenterText(): SpannableString? {
        val s = SpannableString("Ocorrências \nde todo o estado de SP")
        s.setSpan(RelativeSizeSpan(2f), 0, 11, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 11, s.length, 0)
        return s
    }

    private fun generatePieData(category: List<CategoryResponse>): PieData? {
        val entries1 = ArrayList<Entry>()
        val values = ArrayList<String>()

        var i = 0
        for (item in category) {
            if(item.total > 0) {
                values.add(item.name)
                entries1.add(Entry(item.total.toFloat(), i))
                i++
            }
        }

        val ds1 = PieDataSet(entries1, "")
        ds1.setDrawValues(true)
        ds1.setColors(ColorTemplate.PASTEL_COLORS)
        ds1.valueTextColor = Color.WHITE
        ds1.valueTextSize = 12f

        return PieData(values, ds1)
    }
}
