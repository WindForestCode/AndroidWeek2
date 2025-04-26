package com.myschool.app2.repository

import com.myschool.app2.database.UserDao
import com.myschool.app2.model.User
import com.myschool.app2.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomUsersRepository(private val dao: UserDao) : DbUsersRepository {

    override fun getUsers(): Flow<List<User>> = dao.getAll()
        .map { it.map(UserEntity::toUser) }


    override fun saveUser(user: User) {
        dao.save(UserEntity.fromUser(user))
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

    override fun getUser(): User? {
        return if (dao.isEmpty()) {
            null
        } else {
            dao.getUser()?.toUser()
        }
    }
}