package com.carefer.basemodule.data.helpers.network

import com.carefer.basemodule.BuildConfig
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

    fun getHeaders(
        request: Request
    ): Headers {
        val headers = request.headers.newBuilder()
        headers["X-Auth-Token"] = BuildConfig.API_KEY
        return headers.build()
    }

    inline fun <reified T> getService(): T {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY

        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            request = request.newBuilder().headers(
                getHeaders(
                    request
                )
            ).build()
            chain.proceed(request)
        }

        val clientBuilder = okHttpClient?.newBuilder() ?: OkHttpClient.Builder()
        clientBuilder.interceptors().clear()
        clientBuilder.networkInterceptors().clear()

        clientBuilder.addInterceptor(headerAuthorizationInterceptor)

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