package com.assignment.assignmentapplication.utilFiles

import java.text.NumberFormat
import java.util.*

class AppUtil{
    companion object{
        fun convertNumberFormatted(input:Long):String{
            return NumberFormat.getNumberInstance(Locale.US).format(input)
        }
    }
}