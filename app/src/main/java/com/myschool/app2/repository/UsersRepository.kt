package com.myschool.app2.repository

import com.myschool.app2.model.User
import com.myschool.app2.util.Callback

interface UsersRepository {
    fun getUser(callback: Callback<User>)
}