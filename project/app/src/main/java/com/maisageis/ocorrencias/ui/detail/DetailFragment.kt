package com.maisageis.ocorrencias.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.BORelRequest
import com.maisageis.ocorrencias.model.response.CategoryResponse
import com.maisageis.ocorrencias.model.response.CityResponse
import com.maisageis.ocorrencias.model.response.CoodResponse
import com.maisageis.ocorrencias.ui.action.CategoryViewAction
import com.maisageis.ocorrencias.ui.action.DetailsViewAction
import com.maisageis.ocorrencias.util.LoadShimmer
import com.maisageis.ocorrencias.util.LocationStatus
import com.maisageis.ocorrencias.util.ShowAlert
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class DetailFragment : Fragment() {

    private lateinit var chart: BarChart
    private lateinit var line: LineChart

    private lateinit var mView: View
    private var categorySelected: String = ""

    private lateinit var imgTop: ImageView
    private lateinit var title: TextView
    private lateinit var details: TextView
    private lateinit var locTitle: TextView
    private lateinit var locDescription: TextView
    private lateinit var relCity: TextView
    private lateinit var relDistrict: TextView
    private lateinit var relStreet: TextView
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var layoutView: NestedScrollView
    private lateinit var recycle: RecyclerView

    private val detailsViewModel: DetailsViewModel by viewModel()
    private val locationStatus: LocationStatus by inject()

    private lateinit var adapterCategory: AdapterCategoryDetails
    private lateinit var coodResponse: CoodResponse

    companion object {
        fun newInstance(): DetailFragment = DetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_detail, container, false)

        var bundle = arguments
        if(bundle != null)
            categorySelected = bundle.getString("category").toString()

        initViews()
        setLocation()
        setObservable()
        return mView
    }

    private fun initViews() {
        this.imgTop = mView.findViewById(R.id.detailImg)
        this.title = mView.findViewById(R.id.detailtxtTitle)
        this.locTitle = mView.findViewById(R.id.detailtxtMyLocaTile)
        this.details = mView.findViewById(R.id.detailtxtDetalils)
        this.locDescription = mView.findViewById(R.id.detailtxtMyLocaDetails)
        this.relCity = mView.findViewById(R.id.detailtxtCity)
        this.relDistrict = mView.findViewById(R.id.detailtxtDistrict)
        this.relStreet = mView.findViewById(R.id.detailtxtStreet)
        this.chart = mView.findViewById(R.id.detailchartbar)
        this.line = mView.findViewById(R.id.detaillinebar)
        this.shimmer = mView.findViewById(R.id.shimmer_details)
        this.layoutView = mView.findViewById(R.id.layout_details)
        this.recycle = mView.findViewById(R.id.recycleDetails)
        this.recycle.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)

        loadingPage(true)
        detailsViewModel.getCategory()
    }

    private fun setLocation() {
         locationStatus.configurationService {
             loadData(it.latitude.toString(), it.longitude.toString())
             locationStatus.stopService()
         }
    }

    private fun loadData(latitude: String, longitude: String){
        val value = BORelRequest(Category = categorySelected, Latitude = latitude, Longetude = longitude)
        detailsViewModel.getDetails(value)
    }

    private fun setObservable(){
        detailsViewModel.actionDetailsView.observe(requireActivity(), androidx.lifecycle.Observer { state ->
            when(state) {
                is DetailsViewAction.Success -> this.successDetails(state.item)
                is DetailsViewAction.Error -> this.errorDetails(state.item)
                is DetailsViewAction.Loading -> loadingPage(state.loading)
            }
        })

        detailsViewModel.actionCategoryView.observe(requireActivity(), androidx.lifecycle.Observer { state ->
            when(state) {
                is CategoryViewAction.Success -> this.successCategory(state.item)
                is CategoryViewAction.Error -> this.errorCategory(state.item)
            }
        })
    }

    private fun successDetails(value: CoodResponse) {
        this.coodResponse = value
        Picasso.get().load(coodResponse.category.image).into(imgTop)
        this.title.text = coodResponse.category.name
        this.details.text = coodResponse.category.details
        this.locDescription.text =
            coodResponse.coord.streetLong + " " +
            coodResponse.coord.number  + " \n" +
            coodResponse.coord.bairroLong  + " " +
            coodResponse.coord.cidadeLong  + " " +
            coodResponse.coord.estadoLong  + " " +
            coodResponse.coord.cep
        this.relCity.text = coodResponse.details.city.toString()
        this.relDistrict.text = coodResponse.details.districty.toString()
        this.relStreet.text = coodResponse.details.street.toString()

        loadGraphic()
        loadingPage(false)
    }

    private fun errorDetails(ex: ErrorResponse){
        loadingPage(false)
        ShowAlert(requireContext(), "Error", ex.message, {}, icon = "error")
    }

    private fun loadingPage(visible: Boolean) {
        LoadShimmer(shimmer, visible, viewLayout = layoutView)
    }

    private fun successCategory(category: List<CategoryResponse>) {
        adapterCategory = AdapterCategoryDetails(
            requireContext(),
            category
        )
        {
            var bundle: Bundle = Bundle()
            bundle.putString("category", it.id.toString())
            val details = newInstance()
            details.arguments = bundle

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, details)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        recycle.apply {
            adapter = adapterCategory
        }
    }

    private fun errorCategory(ex: ErrorResponse){
    }


    private fun loadGraphic() {
        if (coodResponse.city.isEmpty())
            chart.setDescription("Nenhuma ocorrência encontrada")
        else
            chart.setDescription("")

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.legend.isEnabled = false

        chart.data = generateBarData()

        if (coodResponse.street.isEmpty())
            line.setDescription("Nenhuma ocorrência encontrada")
        else
            chart.setDescription("")

        line.setDrawGridBackground(false)
        line.legend.isEnabled = false

        line.data = generateLineData()
    }

    private fun generateLineData(): LineData{
        val entries = ArrayList<Entry>()
        val labels = ArrayList<String>()

        if(coodResponse.street.isEmpty()){
            labels.add("---")
            labels.add("---")
            entries.add(BarEntry(0f, 0))
            entries.add(BarEntry(0f, 1))
        }
        else {
            coodResponse.street.forEachIndexed { i, item ->
                entries.add(BarEntry(item.total.toFloat(), i))
                labels.add(item.date.substring(0, 10))
            }
        }


        /*
        Mock Data
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")

        entries.add(BarEntry(8f, 0))
        entries.add(BarEntry(2f, 1))
        entries.add(BarEntry(5f, 2))
        entries.add(BarEntry(20f, 3))
        entries.add(BarEntry(15f, 4))
        entries.add(BarEntry(19f, 5))
         */
        val barDataSet = LineDataSet(entries, "Cells")
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS)

        return LineData(labels, barDataSet)
    }

    private fun generateBarData(): BarData? {
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()

        coodResponse.city.forEachIndexed { i, item ->
            entries.add(BarEntry(item.total.toFloat(), i))
            labels.add(item.date.substring(0,10))
        }

        val barDataSet = BarDataSet(entries, "Cells")
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS)

        return BarData(labels, barDataSet)
    }


}