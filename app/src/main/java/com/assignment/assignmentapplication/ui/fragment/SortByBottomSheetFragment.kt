package com.assignment.assignmentapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.assignment.assignmentapplication.R
import com.assignment.assignmentapplication.interfacecallbacks.SortOptionSelectionInterface
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_sort_bottom_layout.*

class SortByBottomSheetFragment(var type: Int, var callback: SortOptionSelectionInterface) :
    BottomSheetDialogFragment() {

    companion object {
        const val TAG = "SortByBottomSheetFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_sort_bottom_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Display the previous selection for the sort
        when (type) {
            1 -> rbTotalSales_SortDialog.isChecked = true
            2 -> rbAddToCart_SortDialog.isChecked = true
            3 -> rbDownload_SortDialog.isChecked = true
            4 -> rbUserSessions_SortDialog.isChecked = true
        }

        //sort selection checked according to that display the list with help of callback
        rdg_SortDialog.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbTotalSales_SortDialog -> {
                    callback.sortByOptionSelection(1)
                    dialog?.dismiss()
                }
                R.id.rbAddToCart_SortDialog -> {
                    callback.sortByOptionSelection(2)
                    dialog?.dismiss()
                }
                R.id.rbDownload_SortDialog -> {
                    callback.sortByOptionSelection(3)
                    dialog?.dismiss()
                }
                R.id.rbUserSessions_SortDialog -> {
                    callback.sortByOptionSelection(4)
                    dialog?.dismiss()
                }
                else -> {
                    callback.sortByOptionSelection(0)
                    dialog?.dismiss()
                }
            }
        })
    }
}