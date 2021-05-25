package com.maisageis.ocorrencias.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.maisageis.ocorrencias.R
import java.util.*


class DetailFragment : Fragment() {

    private lateinit var chart: BarChart
    private lateinit var line: LineChart

    private lateinit var mView: View

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
        xxx()
        return mView
    }

    fun xxx() {
        chart = mView.findViewById(R.id.detailchartbar)
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        chart.data = generateBarData(1, 20000F, 6)


        line = mView.findViewById(R.id.detaillinebar)
        line.setDrawGridBackground(false);

        line.data = generateLineData()
    }

    fun generateLineData(): LineData{
        val entries = ArrayList<Entry>()
        entries.add(BarEntry(8f, 0))
        entries.add(BarEntry(2f, 1))
        entries.add(BarEntry(5f, 2))
        entries.add(BarEntry(20f, 3))
        entries.add(BarEntry(15f, 4))
        entries.add(BarEntry(19f, 5))

        val barDataSet = LineDataSet(entries, "Cells")
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS)
        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")
        val data = LineData(labels, barDataSet)

        return data
    }

    fun generateBarData(dataSets: Int, range: Float, count: Int): BarData? {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(8f, 0))
        entries.add(BarEntry(2f, 1))
        entries.add(BarEntry(5f, 2))
        entries.add(BarEntry(20f, 3))
        entries.add(BarEntry(15f, 4))
        entries.add(BarEntry(19f, 5))

        val barDataSet = BarDataSet(entries, "Cells")
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS)
        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")
        val data = BarData(labels, barDataSet)

        return data
    }


}