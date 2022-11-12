package com.carefer.basemodule.data.helpers.network

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitFactory @Inject constructor() {

    val BASE_URL = "https://api.football-data.org/v2/"
    val gsonConverterFactory = GsonConverterFactory.create()
    var okHttpClient : OkHttpClient? = null


    inline fun <reified T> getService(): T {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = okHttpClient?.newBuilder() ?: OkHttpClient.Builder()
        clientBuilder.interceptors().clear()
        clientBuilder.networkInterceptors().clear()

        // to be enhanced later by using builder
        var timeout = 30L

        clientBuilder.addInterceptor(logging)

        clientBuilder
            .readTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
        okHttpClient = clientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build().create(T::class.java)
    }

}