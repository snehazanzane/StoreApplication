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
import com.assignment.assignmentapplication.ui.fragment.SortByBottomSheetFragment
import com.assignment.assignmentinnofiedsolutionpvtltd.network.AppsStoreAPI
import com.assignment.assignmentinnofiedsolutionpvtltd.utilFiles.NetworkConnectivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaderBoardActivity : AppCompatActivity(), View.OnClickListener {
    var arrApps: ArrayList<AppsMainModel> = ArrayList()
    lateinit var adapterApps: AppsAdapter

    lateinit var dialogSort: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        //** Set the data for our ArrayList
        getItemsData()

        setSortBottomSheetDialog()

        buttonSort_Toolbar.setOnClickListener(View.OnClickListener {
            SortByBottomSheetFragment().apply {
                show(supportFragmentManager, SortByBottomSheetFragment.TAG)
            }
        })
    }

    private fun setSortBottomSheetDialog() {


        /*val laySortdialog = layoutInflater.inflate(R.layout.lay_sort_dialog, null)
        dialogSort = BottomSheetDialog(this)
        dialogSort.setContentView(laySortdialog)
        laySortdialog.setOnClickListener {
            dialogSort.dismiss()
        }
*/


      //  buttonSort_Toolbar.setOnClickListener(View.OnClickListener { dialogSort.show() })


        /* bottomSheetBehavior = BottomSheetBehavior.from(bottomSort_SortDialog)

         bottomSheetBehavior.addBottomSheetCallback(object :
             BottomSheetBehavior.BottomSheetCallback() {

             override fun onSlide(bottomSheet: View, slideOffset: Float) {
                 // handle onSlide
             }

             override fun onStateChanged(bottomSheet: View, newState: Int) {
                 when (newState) {
                     BottomSheetBehavior.STATE_COLLAPSED -> Toast.makeText(this@LeaderBoardActivity, "STATE_COLLAPSED", Toast.LENGTH_SHORT).show()
                     BottomSheetBehavior.STATE_EXPANDED -> Toast.makeText(this@LeaderBoardActivity, "STATE_EXPANDED", Toast.LENGTH_SHORT).show()
                     BottomSheetBehavior.STATE_DRAGGING -> Toast.makeText(this@LeaderBoardActivity, "STATE_DRAGGING", Toast.LENGTH_SHORT).show()
                     BottomSheetBehavior.STATE_SETTLING -> Toast.makeText(this@LeaderBoardActivity, "STATE_SETTLING", Toast.LENGTH_SHORT).show()
                     BottomSheetBehavior.STATE_HIDDEN -> Toast.makeText(this@LeaderBoardActivity, "STATE_HIDDEN", Toast.LENGTH_SHORT).show()
                     else -> Toast.makeText(this@LeaderBoardActivity, "OTHER_STATE", Toast.LENGTH_SHORT).show()
                 }
             }
         })

         buttonSort_Toolbar.setOnClickListener {
             if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
                 bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
             else
                 bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
         }*/

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
        adapterApps = AppsAdapter(this@LeaderBoardActivity, arrApps)
        adapterApps.notifyDataSetChanged()
        recyclerView_LeaderBoardActivity.adapter = adapterApps
        setRVLayoutManager()
    }


    fun setRVLayoutManager() {
        val mLayoutManager = LinearLayoutManager(this)
        /* mLayoutManager.reverseLayout = true
         mLayoutManager.stackFromEnd = true*/
        recyclerView_LeaderBoardActivity.layoutManager = mLayoutManager
        recyclerView_LeaderBoardActivity.setHasFixedSize(true)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //  R.id.buttonSort_Toolbar ->
            // dialogSort?.show()
            // Toast.makeText(this, "Sort is clicked", Toast.LENGTH_SHORT).show()

        }
    }


}