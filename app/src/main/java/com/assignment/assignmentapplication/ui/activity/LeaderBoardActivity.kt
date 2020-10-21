package com.assignment.assignmentapplication.ui.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.assignmentapplication.R
import com.assignment.assignmentapplication.databinding.adapters.AppsAdapter
import com.assignment.assignmentapplication.databinding.models.AppsListModel
import com.assignment.assignmentapplication.databinding.models.AppsMainModel
import com.assignment.assignmentapplication.interfacecallbacks.SortOptionSelectionInterface
import com.assignment.assignmentapplication.ui.fragment.SortByBottomSheetFragment
import com.assignment.assignmentinnofiedsolutionpvtltd.network.AppsStoreAPI
import com.assignment.assignmentinnofiedsolutionpvtltd.utilFiles.NetworkConnectivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaderBoardActivity : AppCompatActivity(), View.OnClickListener,
    SortOptionSelectionInterface {
    var arrApps: ArrayList<AppsMainModel> = ArrayList()
    lateinit var adapterApps: AppsAdapter

    var sortByType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        //** Set the data for our ArrayList
        getItemsData()

        buttonSort_Toolbar.setOnClickListener(this)
    }


    /**
     * Get Apps List Data from Rest API
     */
    private fun getItemsData() {

        if (NetworkConnectivity.isConnected(this@LeaderBoardActivity)) {

            //Get data from Rest API Call -->>
            val progressDialog = ProgressDialog(this@LeaderBoardActivity)
            progressDialog.setTitle("Please wait...")
            progressDialog.show()

            AppsStoreAPI().getApps()
                .enqueue(object : Callback<AppsListModel> {
                    override fun onFailure(call: Call<AppsListModel>, t: Throwable) {
                        progressDialog.dismiss()
                        Toast.makeText(
                            this@LeaderBoardActivity,
                            getString(R.string.msg_unable_to_fetch_data),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<AppsListModel>,
                        response: Response<AppsListModel>
                    ) {
                        response.body()?.let {

                            progressDialog.dismiss()
                            if (it.apps.size > 0) {

                                arrApps = it.apps

                                //** Set the adapter of the RecyclerView
                                setAdapter()
                            } else {
                                Toast.makeText(
                                    this@LeaderBoardActivity,
                                    getString(R.string.msg_no_data_available),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }
                    }
                })
        } else {
            NetworkConnectivity.showNetworkAlert(this@LeaderBoardActivity)
        }
    }

    /**
     * Binding apps data to Adapter
     */
    fun setAdapter() {

        when (sortByType) {
            1 -> {
                var sortedList = arrApps.sortedWith(compareBy({ it.data.total_sale.total }))
                arrApps.clear()
                arrApps.addAll(sortedList)
            }
            2 -> {
                var sortedList = arrApps.sortedWith(compareBy({ it.data.add_to_cart.total }))
                arrApps.clear()
                arrApps.addAll(sortedList)
            }
            3 -> {
                var sortedList = arrApps.sortedWith(compareBy({ it.data.downloads.total }))
                arrApps.clear()
                arrApps.addAll(sortedList)
            }
            4 -> {
                var sortedList = arrApps.sortedWith(compareBy({ it.data.sessions.total }))
                arrApps.clear()
                arrApps.addAll(sortedList)
            }
        }


        adapterApps = AppsAdapter(this@LeaderBoardActivity, arrApps)
        adapterApps.notifyDataSetChanged()
        recyclerView_LeaderBoardActivity.adapter = adapterApps
        setRVLayoutManager()
    }

    fun setRVLayoutManager() {
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView_LeaderBoardActivity.layoutManager = mLayoutManager
        recyclerView_LeaderBoardActivity.setHasFixedSize(true)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSort_Toolbar -> {
                SortByBottomSheetFragment(sortByType, this@LeaderBoardActivity).apply {
                    show(supportFragmentManager, SortByBottomSheetFragment.TAG)
                }
            }


        }
    }

    override fun sortByOptionSelection(type: Int) {
        sortByType = type
        setAdapter()
    }


}