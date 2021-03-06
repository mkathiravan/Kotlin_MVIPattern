package com.example.myapplication

import com.example.myapplication.app.arch.IIntent

sealed class UserIntent : IIntent
{
    object RefreshUsers : UserIntent()
    object FetchUsers : UserIntent()
}