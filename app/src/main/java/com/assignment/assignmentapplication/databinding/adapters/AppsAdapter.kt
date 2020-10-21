package com.assignment.assignmentapplication.databinding.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.assignment.assignmentapplication.R
import com.assignment.assignmentapplication.databinding.models.AppsMainModel
import kotlinx.android.synthetic.main.view_store_list_item.view.*

class AppsAdapter(var context: Context, private var arrApps: ArrayList<AppsMainModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_store_list_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrApps.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        itemViewHolder.itemView.textStoreName_StoreListItem.text =
            arrApps.get(position)?.name
        itemViewHolder.itemView.textTotalSalesGDP_StoreListItem.text =
            context.getString(R.string.str_GDP) + " " +
                    arrApps.get(position)?.currency + " " +
                    arrApps.get(position)?.data?.total_sale?.total


        itemViewHolder.itemView.textViewDetails_StoreListItem.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"View details clicked : "+arrApps.get(position)?.name,Toast.LENGTH_SHORT).show()
        })
    }
}