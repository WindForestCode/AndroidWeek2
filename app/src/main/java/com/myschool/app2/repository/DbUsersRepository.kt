package com.myschool.app2.repository

import com.myschool.app2.model.User
import kotlinx.coroutines.flow.Flow


interface DbUsersRepository {
    fun getUsers(): Flow<List<User>>
    fun saveUser(user: User)
    fun deleteAll()
}