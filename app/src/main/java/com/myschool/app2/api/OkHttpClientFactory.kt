package com.myschool.app2.api

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {
    val key = "xbueKGzLSqYw4ISlRvea6w==Ce8IPZCXfnf7flF1"

    val INSTANCE by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .addHeader("X-Api-key", key)
                        .build()
                )
            }
            .build()

    }
}