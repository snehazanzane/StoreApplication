package com.assignment.assignmentapplication.databinding.models

import com.google.gson.annotations.SerializedName

class DataDetailsModel {

    @SerializedName("total")
    var total: Long = 0

    @SerializedName("month_wise")
    var month_wise: MonthWiseModel = MonthWiseModel()

}