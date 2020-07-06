package com.example.myapplication.app.di

import com.example.myapplication.model.UserApi
import okhttp3.OkHttpClient
import org.ericampire.android.demoapp.viewmodel.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val netModule = module {
    fun providerOkHttpClient() : OkHttpClient
    {
        return OkHttpClient.Builder().build()
    }

    fun provideRetrofit(client: OkHttpClient) : Retrofit
    {
        return Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client).build()
    }

    single { providerOkHttpClient() }

    single { provideRetrofit(get()) }
}

val viewModelScope = module {
    viewModel { UserViewModel(get()) }
}

val apiModule = module {

    fun provideUserApi(retrofit: Retrofit) : UserApi
    {
        return retrofit.create(UserApi::class.java)
    }

    single { provideUserApi(get()) }
}