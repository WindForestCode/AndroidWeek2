package com.myschool.app2.database

import androidx.room.Dao
import androidx.room.Query
import com.myschool.app2.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): Flow<List<UserEntity>>
}