package com.assignment.assignmentapplication.utilFiles

import java.text.NumberFormat
import java.util.*

class AppUtil {
    companion object {
        /**
         * Function is use to format the number
         * @param input : long number
         * @return val : String with commas format
         */
        fun convertNumberFormatted(input: Long): String {
            return NumberFormat.getNumberInstance(Locale.US).format(input)
        }
    }
}