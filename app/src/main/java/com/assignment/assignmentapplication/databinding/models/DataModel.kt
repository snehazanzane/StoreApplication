package com.assignment.assignmentapplication.databinding.models

import com.google.gson.annotations.SerializedName

class DataModel {
    @SerializedName("downloads")
    var downloads: DataDetailsModel = DataDetailsModel()

    @SerializedName("sessions")
    var sessions: DataDetailsModel = DataDetailsModel()

    @SerializedName("total_sale")
    var total_sale: DataDetailsModel = DataDetailsModel()

    @SerializedName("add_to_cart")
    var add_to_cart: DataDetailsModel = DataDetailsModel()
}