package com.myschool.app2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myschool.app2.model.User
import com.myschool.app2.repository.RoomUsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class UserViewModel( private val roomRepository: RoomUsersRepository): ViewModel() {
        private val _uiState = MutableStateFlow(UserUiState())
        val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    init {
        roomRepository.getUsers()
            .onEach { users ->
                _uiState.update {
                    it.copy(user = users)

                }
            }.launchIn(viewModelScope)
    }

    fun saveUser(user: User){
        roomRepository.saveUser(user)
        roomRepository.getUsers().onEach { users ->
            _uiState.update { it.copy(user = users) }
        }.launchIn(viewModelScope)

    }

    fun delete(){
        roomRepository.deleteAll()
    }

}