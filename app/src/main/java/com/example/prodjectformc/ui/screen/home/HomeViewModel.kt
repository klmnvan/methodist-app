package com.example.prodjectformc.ui.screen.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.home.HomeState
import com.example.prodjectformc.data.network.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    var state by mutableStateOf(HomeState())

    init {
        viewModelScope.launch {
            CurrentUser.accountInfo = service.getAccountInfo(CurrentUser.token)
            Log.d("accountInfo", CurrentUser.accountInfo.toString())
            if(CurrentUser.accountInfo != null){
                CurrentUser.listEvents = service.getEvent((CurrentUser.accountInfo!!.id))?.toMutableList()
            }
        }
    }



}