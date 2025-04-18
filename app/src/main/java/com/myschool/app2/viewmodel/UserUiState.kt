package com.myschool.app2.viewmodel

import com.myschool.app2.model.User

data class UserUiState(
    val user: List<User> = emptyList()
)