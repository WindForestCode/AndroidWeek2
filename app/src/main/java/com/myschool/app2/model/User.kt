package com.myschool.app2.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("username")
    val username: String,
    @SerialName("sex")
    val sex: String,
    @SerialName("address")
    val address: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("birthday")
    val birthday: String,
)
