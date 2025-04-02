package com.myschool.app2.api

import com.myschool.app2.model.User
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET

interface UsersApi {

    @GET("v1/randomuser")
    fun getUser(): Call<User>

    companion object {
        val INSTANCE: UsersApi by lazy {
            RetrofitFactory.INSTANCE.create()
        }
    }
}