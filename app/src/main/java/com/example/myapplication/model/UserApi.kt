package com.example.myapplication.model

import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<User>
}