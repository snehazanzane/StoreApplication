package com.assignment.assignmentapplication.databinding.models

import com.google.gson.annotations.SerializedName

class AppsListModel {
    @SerializedName("apps")
    var apps: ArrayList<AppsMainModel> = ArrayList()
}