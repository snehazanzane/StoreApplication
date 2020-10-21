package com.assignment.assignmentapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import com.assignment.assignmentapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.lay_sort_dialog.*

class SortByBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {

        const val TAG = "CustomBottomSheetDialogFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lay_sort_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rdg_SortDialog.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbTotalSales_SortDialog -> {
                    Toast.makeText(activity, "Sales selected", Toast.LENGTH_SHORT).show()
                    dialog?.dismiss()
                }
                R.id.rbAddToCart_SortDialog ->
                    Toast.makeText(activity, "Add to cart selected", Toast.LENGTH_SHORT).show()
                R.id.rbDownload_SortDialog ->
                    Toast.makeText(activity, "Download selected", Toast.LENGTH_SHORT).show()
                R.id.rbUserSessions_SortDialog ->
                    Toast.makeText(activity, "User sessions selected", Toast.LENGTH_SHORT).show()
            }

        })

        /*firstButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "First Button Clicked", Toast.LENGTH_SHORT).show()
        }
        secondButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "Second Button Clicked", Toast.LENGTH_SHORT).show()
        }
        thirdButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "Third Button Clicked", Toast.LENGTH_SHORT).show()
        }*/

    }
}