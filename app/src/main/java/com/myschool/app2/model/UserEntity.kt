package com.myschool.app2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")

data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("username")
    val username: String,
    @ColumnInfo("sex")
    val sex: String,
    @ColumnInfo("address")
    val address: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("birthday")
    val birthday: String,
)
{
    companion object{

        fun fromUser(user: User): UserEntity = with(user){
            UserEntity(
                id = id,
                username = username,
                sex = sex,
                address = address,
                name = name,
                email = email,
                birthday = birthday,
            )

        }
    }

    fun toUser(): User = User(
        username = username,
        sex = sex,
        address = address,
        name = name,
        email = email,
        birthday = birthday,
    )
}
