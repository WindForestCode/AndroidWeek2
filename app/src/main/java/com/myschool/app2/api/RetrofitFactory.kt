package com.myschool.app2.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

object RetrofitFactory {
    private val contentType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }

    val INSTANCE: Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClientFactory.INSTANCE)
            .baseUrl("https://api.api-ninjas.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}