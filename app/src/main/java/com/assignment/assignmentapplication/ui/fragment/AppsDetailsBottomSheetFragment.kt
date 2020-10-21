package com.assignment.assignmentapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.assignment.assignmentapplication.R
import com.assignment.assignmentapplication.databinding.models.AppsMainModel
import com.assignment.assignmentapplication.utilFiles.AppUtil
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_apps_details_layout.*
import kotlin.collections.ArrayList


class AppsDetailsBottomSheetFragment(var obj: AppsMainModel) :
    BottomSheetDialogFragment() {

    companion object {
        const val TAG = "AppsDetailsBottomSheetFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_apps_details_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textAppName_AppDetailsDialog.text = obj?.name

        //Setting default selection of tab
        textValueTitle_AppDetailsDialog.text = activity?.getString(R.string.str_tlt_total_sales)
        textValue_AppDetailsDialog.text =
            AppUtil.convertNumberFormatted(obj?.data?.total_sale?.total)
        setGraph(1)

        //Tab option selection handel with the help of menu id
        bottomNavigationView_AppDetailsDialog.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    textValueTitle_AppDetailsDialog.text =
                        activity?.getString(R.string.str_tlt_total_sales)
                    textValue_AppDetailsDialog.text =
                        AppUtil.convertNumberFormatted(obj?.data?.total_sale?.total)
                    setGraph(1)
                    true
                }
                R.id.menu_notification -> {
                    textValueTitle_AppDetailsDialog.text =
                        activity?.getString(R.string.str_tlt_add_to_cart)
                    textValue_AppDetailsDialog.text =
                        AppUtil.convertNumberFormatted(obj?.data?.add_to_cart?.total)
                    setGraph(2)
                    true
                }
                R.id.menu_search -> {
                    textValueTitle_AppDetailsDialog.text =
                        activity?.getString(R.string.str_tlt_download)
                    textValue_AppDetailsDialog.text =
                        AppUtil.convertNumberFormatted(obj?.data?.downloads?.total)
                    setGraph(3)
                    true
                }
                R.id.menu_profile -> {
                    textValueTitle_AppDetailsDialog.text =
                        activity?.getString(R.string.str_tlt_user_sessions)
                    textValue_AppDetailsDialog.text =
                        AppUtil.convertNumberFormatted(obj?.data?.sessions?.total)
                    setGraph(4)
                    true
                }
                else -> false
            }
        }
    }

    fun setGraph(type: Int) {
        var lineDataSet = LineDataSet(getSalesData(), "Apps")

        when (type) {
            1 -> lineDataSet =
                LineDataSet(getSalesData(), activity?.getString(R.string.str_tlt_total_sales))
            2 -> lineDataSet =
                LineDataSet(getAddToCartData(), activity?.getString(R.string.str_tlt_add_to_cart))
            3 -> lineDataSet =
                LineDataSet(getDownloadData(), activity?.getString(R.string.str_tlt_download))
            4 -> lineDataSet =
                LineDataSet(getUserSessions(), activity?.getString(R.string.str_tlt_user_sessions))
        }

        //Designing the graph
        lineDataSet.color = activity?.let { ContextCompat.getColor(it, R.color.graph_end_color) }!!
        lineDataSet.valueTextColor = ContextCompat.getColor(activity!!, R.color.graph_start_color)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        lineDataSet.lineWidth = 3f
        //lineDataSet.fillColor = R.color.graph_start_color
        lineDataSet.fillAlpha = 50

        val xAxis: XAxis = lineChart_AppDetailsDialog.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        //Setting horizontal value
        val months = arrayOf("", "Jan", "Feb", "Mar", "Apr", "May", "Jun")
        val formatter: ValueFormatter =
            object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase): String {
                    return months[value.toInt()]
                }
            }
        xAxis.granularity = 1f
        xAxis.valueFormatter = formatter

        val yAxisRight: YAxis = lineChart_AppDetailsDialog.getAxisRight()
        yAxisRight.isEnabled = false

        val yAxisLeft: YAxis = lineChart_AppDetailsDialog.getAxisLeft()
        yAxisLeft.granularity = 0f

        lineChart_AppDetailsDialog.xAxis.setDrawGridLines(false)
        lineChart_AppDetailsDialog.axisLeft.setDrawGridLines(false)
        lineChart_AppDetailsDialog.axisRight.setDrawGridLines(false)

        val rightYAxis = lineChart_AppDetailsDialog.axisRight
        rightYAxis.isEnabled = false
        val leftYAxis = lineChart_AppDetailsDialog.axisLeft
        leftYAxis.isEnabled = true
        val topXAxis = lineChart_AppDetailsDialog.xAxis
        topXAxis.isEnabled = true

        //Setting graph data
        val data = LineData(lineDataSet)
        lineChart_AppDetailsDialog.setData(data)
        lineChart_AppDetailsDialog.animateX(1800, Easing.EaseInOutBounce)
        lineChart_AppDetailsDialog.legend.isEnabled = false
        lineChart_AppDetailsDialog.description.isEnabled = false
        lineChart_AppDetailsDialog.invalidate()
    }

    private fun getSalesData(): ArrayList<Entry>? {
        val entries: ArrayList<Entry> = ArrayList()
        entries.add(Entry(1f, obj?.data?.total_sale?.month_wise?.jan.toFloat()))
        entries.add(Entry(2f, obj?.data?.total_sale?.month_wise?.feb.toFloat()))
        entries.add(Entry(3f, obj?.data?.total_sale?.month_wise?.mar.toFloat()))
        entries.add(Entry(4f, obj?.data?.total_sale?.month_wise?.apr.toFloat()))
        entries.add(Entry(5f, obj?.data?.total_sale?.month_wise?.mar.toFloat()))
        entries.add(Entry(6f, obj?.data?.total_sale?.month_wise?.jun.toFloat()))
        return entries
    }

    private fun getAddToCartData(): ArrayList<Entry>? {
        val entries: ArrayList<Entry> = ArrayList()
        entries.add(Entry(1f, obj?.data?.add_to_cart?.month_wise?.jan.toFloat()))
        entries.add(Entry(2f, obj?.data?.add_to_cart?.month_wise?.feb.toFloat()))
        entries.add(Entry(3f, obj?.data?.add_to_cart?.month_wise?.mar.toFloat()))
        entries.add(Entry(4f, obj?.data?.add_to_cart?.month_wise?.apr.toFloat()))
        entries.add(Entry(5f, obj?.data?.add_to_cart?.month_wise?.mar.toFloat()))
        entries.add(Entry(6f, obj?.data?.add_to_cart?.month_wise?.jun.toFloat()))
        return entries
    }

    private fun getDownloadData(): ArrayList<Entry>? {
        val entries: ArrayList<Entry> = ArrayList()
        entries.add(Entry(1f, obj?.data?.downloads?.month_wise?.jan.toFloat()))
        entries.add(Entry(2f, obj?.data?.downloads?.month_wise?.feb.toFloat()))
        entries.add(Entry(3f, obj?.data?.downloads?.month_wise?.mar.toFloat()))
        entries.add(Entry(4f, obj?.data?.downloads?.month_wise?.apr.toFloat()))
        entries.add(Entry(5f, obj?.data?.downloads?.month_wise?.mar.toFloat()))
        entries.add(Entry(6f, obj?.data?.downloads?.month_wise?.jun.toFloat()))
        return entries
    }

    private fun getUserSessions(): ArrayList<Entry>? {
        val entries: ArrayList<Entry> = ArrayList()
        entries.add(Entry(1f, obj?.data?.sessions?.month_wise?.jan.toFloat()))
        entries.add(Entry(2f, obj?.data?.sessions?.month_wise?.feb.toFloat()))
        entries.add(Entry(3f, obj?.data?.sessions?.month_wise?.mar.toFloat()))
        entries.add(Entry(4f, obj?.data?.sessions?.month_wise?.apr.toFloat()))
        entries.add(Entry(5f, obj?.data?.sessions?.month_wise?.mar.toFloat()))
        entries.add(Entry(6f, obj?.data?.sessions?.month_wise?.jun.toFloat()))
        return entries
    }


}