package com.example.prodjectformc.ui.screen.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.profile.ProfileState
import com.example.prodjectformc.data.network.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: ProfileState get() = _state.value

    fun updateState(newState: ProfileState) {
        _state.value = newState
    }

    init {
        updateState(
            ProfileState(
            surname = CurrentUser.accountInfo?.surname!!,
            name = CurrentUser.accountInfo?.name!!,
            patronymic = CurrentUser.accountInfo?.patronymic!!)
        )
    }


}