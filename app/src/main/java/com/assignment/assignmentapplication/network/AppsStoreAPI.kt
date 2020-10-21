package com.assignment.assignmentinnofiedsolutionpvtltd.network

import com.assignment.assignmentapplication.databinding.models.AppsListModel
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AppsStoreAPI {

    @GET(ApiUrl.GET_APPS_DATA_API)
    fun getApps(): Call<AppsListModel>

    companion object {
        operator fun invoke(): AppsStoreAPI {

            //For timeout functionality
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppsStoreAPI::class.java)

        }
    }
}