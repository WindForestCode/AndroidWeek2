package com.myschool.app2.repository

import com.myschool.app2.api.UsersApi
import com.myschool.app2.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NetworkUsersRepository(private val api: UsersApi) : UsersRepository {

    override fun getUser(callback: com.myschool.app2.util.Callback<User>) {
        api.getUser().enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            callback.onError(RuntimeException("Response body is null"))
                            return
                        }
                        callback.onSuccess(body)
                    } else {
                        callback.onError(RuntimeException("Response code: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    callback.onError(t)
                }
            }
        )
    }

}