package com.theo.mynews.services

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    val apiInterface: ApiInterface
    private val retrofit: Retrofit
    private val DEFAULT_TIMEOUT = 5L
    private val okHttpClient: OkHttpClient

    init {
        val logging = Interceptor { chain ->
            val request = chain.request()
            Log.d("okhttp", "okhttp--->" + request.url().toString())
            chain.proceed(request)
        }
        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(HttpConstants.BASE_URL)
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }
}