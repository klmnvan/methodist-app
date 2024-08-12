package com.example.prodjectformc.ui.screen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.prodjectformc.data.model.profile.ProfileState
import com.example.prodjectformc.data.network.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    var state by mutableStateOf(ProfileState())


}