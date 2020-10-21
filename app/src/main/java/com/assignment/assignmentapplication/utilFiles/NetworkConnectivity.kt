package com.assignment.assignmentinnofiedsolutionpvtltd.utilFiles

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.assignment.assignmentapplication.R

class NetworkConnectivity {

    companion object Factory {
        fun create(): NetworkConnectivity = NetworkConnectivity()

        /** 
         * Is connected boolean.
         *
         * @param context the context
         * @return the boolean
         */
        fun isConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            if (null != activeNetwork) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) return activeNetwork.isConnected
                if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) return activeNetwork.isConnected
            }
            return false
        }

        /**
         * Show alert.
         *
         * @param context the context
         */
        fun showNetworkAlert(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("")
            builder.setMessage(context.getString(R.string.No_internet_connection))
                .setCancelable(false)
                .setPositiveButton(
                    context.getString(R.string.str_OK)
                ) { dialog, id -> //do things
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
            val pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
            pbutton.setTextColor(ContextCompat.getColor(context, R.color.blue))
        }
    }

}