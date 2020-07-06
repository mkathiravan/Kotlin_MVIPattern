package org.ericampire.android.demoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UserIntent
import com.example.myapplication.UserState
import com.example.myapplication.app.arch.IModel
import com.example.myapplication.model.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch



class UserViewModel(private val userApi: UserApi) : ViewModel(), IModel<UserState, UserIntent> {

    override val intents: Channel<UserIntent> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<UserState>().apply { value = UserState() }

    override val state: LiveData<UserState>
        get() = _state

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { userIntent ->
                when(userIntent) {
                    UserIntent.RefreshUsers -> fetchData()
                    UserIntent.FetchUsers -> fetchData()
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoading = true) }
                updateState { it.copy(isLoading = false, users = userApi.getUsers()) }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }

    private fun updateState(handler: suspend (intent: UserState) -> UserState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = handler(state.value!!)
        }
    }
}