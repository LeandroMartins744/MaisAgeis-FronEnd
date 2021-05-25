package com.maisageis.ocorrencias.ui.location

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.mydata.MyDataFragment
import java.util.ArrayList

class LocationFragment : Fragment() {

    private lateinit var chart: LineChart
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_location, container, false)
        xxx()
        return mView
    }

    companion object {
        fun newInstance(): LocationFragment = LocationFragment()
    }


    fun xxx() {
        chart = mView.findViewById(R.id.locationLineBar)
        chart.setDrawGridBackground(false);

        chart.data = generateLineData()

    }

    fun randomNumber(): Float{
        val start = 10
        val end = 50
        return (start..end).random().toFloat()
    }
    fun generateLineData(): LineData {

        val entries1 = ArrayList<Entry>()
        val entries2 = ArrayList<Entry>()
        val entries3 = ArrayList<Entry>()
        val entries4 = ArrayList<Entry>()
        val entries5 = ArrayList<Entry>()
        val entries6 = ArrayList<Entry>()

        for (i in 0..1) {
            entries1.add(BarEntry(randomNumber(), i))
            entries2.add(BarEntry(randomNumber(), i))
            entries3.add(BarEntry(randomNumber(), i))
            entries4.add(BarEntry(randomNumber(), i))
            entries5.add(BarEntry(randomNumber(), i))
            entries6.add(BarEntry(randomNumber(), i))
        }


        val barDataSet = LineDataSet(entries1, "1111")
        barDataSet.color = resources.getColor(R.color.colorAccent)

        val barDataSet2 = LineDataSet(entries2, "2222")
        barDataSet2.color = resources.getColor(R.color.colorPrimary)

        val barDataSet3 = LineDataSet(entries3, "333")
        barDataSet3.color = resources.getColor(R.color.colorPrimary)

        val barDataSet4 = LineDataSet(entries4, "555")
        barDataSet4.color = resources.getColor(R.color.black)

        val barDataSet5 = LineDataSet(entries5, "7777")
        barDataSet5.color = resources.getColor(R.color.grey_font)

        val barDataSet6 = LineDataSet(entries6, "8888")
        barDataSet6.color = resources.getColor(R.color.colorPrimaryLight)

        var bar: List<LineDataSet> = listOf(barDataSet, barDataSet2, barDataSet3, barDataSet4, barDataSet5, barDataSet6)

        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        //labels.add("20-Jan")
        val data = LineData(labels, bar)

        return data
    }
}