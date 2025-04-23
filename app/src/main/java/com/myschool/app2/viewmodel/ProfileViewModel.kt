package com.myschool.app2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.myschool.app2.model.User
import com.myschool.app2.repository.RoomUsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RoomUsersRepository): ViewModel() {
    private val _uiState =  MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            val sampleUser = User(
                0L, "username", "sex", "address", "name", "email", "birthday")
            _uiState.value = ProfileUiState(sampleUser)
        }
    }


    fun refreshUser() {
        viewModelScope.launch {
            val user = repository.getUser()
            _uiState.value = ProfileUiState(user)

        }
    }


}