package com.assignment.assignmentapplication.databinding.models

import com.google.gson.annotations.SerializedName

class AppsMainModel {
    @SerializedName("name")
    var name: String = ""

    @SerializedName("currency")
    var currency: String = ""

    @SerializedName("money_format")
    var money_format: String = ""

    @SerializedName("data")
    var data: DataModel = DataModel()


}