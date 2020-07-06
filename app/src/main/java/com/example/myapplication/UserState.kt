package com.example.myapplication

import com.example.myapplication.app.arch.IState
import com.example.myapplication.model.User

data class UserState(
    val users: List<User> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) : IState