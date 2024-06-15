package com.example.prodjectformc.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.prodjectformc.data.network.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

}